package jp.co.answernet.boot.core.workstyle;

import jp.co.answernet.boot.core.ApplicationContextProvider;
import jp.co.answernet.boot.core.member.Account;
import jp.co.answernet.boot.core.util.CrudService;
import jp.co.answernet.boot.core.util.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by yasuhiro on 2014/06/07.
 */
@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class TimeCard {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @NaturalId
  @NotNull
  @NonNull
  private Account account;

  @Column
  @NaturalId
  @NotNull
  @NonNull
  private Timestamp onWork;

  @Column
  private Timestamp leave;

  @Autowired
  private transient TimeCardRepository timeCardRepository;



  public TimeCard save() {
    ApplicationContextProvider.autowire(this);
    return timeCardRepository.save(this);
  }



  public static List<TimeCard> getTimeCardsDuring( @NotNull final Account account, @NotNull final LocalDateTime from, final LocalDateTime to ) {
    SimpleJpaRepository<TimeCard, Long> repository = CrudService.<TimeCard, Long>createRepository(TimeCard.class);

    final LocalDateTime to_limit = to == null ? LocalDateTime.MAX : to;
    return repository.findAll(( Root<TimeCard> root, CriteriaQuery<?> query, CriteriaBuilder cb ) ->
    {
      return cb.and(
              cb.between(root.<Timestamp>get("onWork"), TimeUtil.toTimestamp(from), TimeUtil.toTimestamp(to_limit)),
              cb.equal(root.<Account>get("account"), account)
      );
    });
  }


  public static TimeCard onWork( @NotNull final Account account ) {
    LocalDateTime now = LocalDateTime.now();
    TimeCard onWork = getLatest(account);
    if ( onWork.getState().equals(State.READY) ) {
      onWork.onWork = TimeUtil.toTimestamp(now);
      return onWork.save();
    }
    return onWork;
  }


  public static TimeCard leave( @NotNull final Account account ) {
    LocalDateTime now = LocalDateTime.now();
    TimeCard onWork = getLatest(account);
    if ( onWork.getState().equals(State.WORKING) ) {
      onWork.leave = TimeUtil.toTimestamp(now);
      return onWork.save();
    }
    return onWork;
  }


  public static Map<LocalDate, List<TimeCard>> monthlyReport( final LocalDate month, final Account...users ) {
    Timestamp startOfMonth = TimeUtil.toTimestamp(month.withDayOfMonth(1));
    Timestamp endOfMonth   = TimeUtil.toTimestamp(month.withDayOfMonth(month.getMonth().maxLength()));

    SimpleJpaRepository<TimeCard, Long> repository = CrudService.<TimeCard, Long>createRepository(TimeCard.class);
    List<TimeCard> timeCards = repository.findAll(( Root<TimeCard> root, CriteriaQuery<?> query, CriteriaBuilder cb ) -> {
      return cb.and(
              cb.between(root.get("onWork"), startOfMonth, endOfMonth),
              cb.between(root.get("leave"), startOfMonth, endOfMonth),
              cb.in(root.get("account")).value(Arrays.asList(users))
      );
    });
    return timeCards.stream().collect(Collectors.groupingBy( tc -> tc.onWork.toLocalDateTime().toLocalDate()));
  }


  public static List<TimeCard> findByAccountAndOnWorkBetween( final Account account, final LocalDate from, final LocalDate to ) {
    SimpleJpaRepository<TimeCard, Long> repository = CrudService.<TimeCard, Long>createRepository(TimeCard.class);
    List<TimeCard> reports = repository.findAll(( Root<TimeCard> root, CriteriaQuery<?> query, CriteriaBuilder cb ) ->
    {
      return cb.and(
              cb.between(root.get("onWork"), TimeUtil.toTimestamp(from), TimeUtil.toTimestamp(to)),
              cb.equal(root.get("account"), account)
      );
    });
    return reports;
  }

  public static TimeCard getLatest( @NotNull final Account account ) {
    SimpleJpaRepository<TimeCard, Long> repository = CrudService.<TimeCard, Long>createRepository(TimeCard.class);
    TimeCard latest = repository.findOne((Root<TimeCard> root ,CriteriaQuery<?> query, CriteriaBuilder cb)->
    {
      Subquery<Number> subquery = query.subquery(Number.class);
      Root<TimeCard> subRoot = subquery.from(TimeCard.class);
      subquery.select(cb.max(subRoot.get("onWork")))
              .where(cb.equal(subRoot.<Account>get("account"), account));
      query.where(
              cb.and(
                      cb.equal(root.<Account>get("account"), account),
                      cb.equal(root.<Timestamp>get("onWork"), subquery.getSelection())
              ));
      return query.getRestriction();
    });
    if ( latest == null ) {
      latest = ready(account);
    }
    return latest;
  }


  public boolean isEditable() {
    return ! (getState().equals(State.FINISHED) && leave.after(TimeUtil.now(ChronoUnit.DAYS)));
  }


  public State getState() {
    if ( onWork == null ) {
      return State.READY;
    }
    if ( leave == null ) {
      return State.WORKING;
    }
    return State.FINISHED;
  }


  public double getWorkingHours() {
    Duration result = Duration.between(this.onWork.toLocalDateTime(), this.leave.toLocalDateTime());
    long millis = result.toMillis();
    double hours = result.toHours();
    double minutesAsHours = ((double)result.toMinutes() % 60) / 60;

    return hours + minutesAsHours;
  }

  private static TimeCard ready( Account account ) {
    TimeCard ready = new TimeCard();
    ready.account = account;
    return ready;
  }


  public static enum State {
    READY("onWork"),
    WORKING("leave"),
    FINISHED("");

    private String method;

    private State( String method ) {
      this.method = method;
    }

    public String getMethod() {
      return method;
    }
  }
}
