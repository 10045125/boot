package jp.co.answernet.boot.core.workstyle;

import jp.co.answernet.boot.core.member.Account;
import jp.co.answernet.boot.core.util.AccountUtil;
import jp.co.answernet.boot.core.util.CrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * Created by yasuhiro on 2014/06/07.
 */
@Controller
@RequestMapping("/timeCard")
public class TimeCardController {

  @RequestMapping({"/", ""})
  public String index( Map<String, Object> model ) {
    Account user = getAccount();
    LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
    LocalDateTime to   = from.plusMonths(1).truncatedTo(ChronoUnit.DAYS);
    model.put("list", TimeCard.getTimeCardsDuring(user, from, to));
    model.put("onWork", TimeCard.getLatest(user));
    model.put("user", user);
    return "timecard/timecard";
  }


  @RequestMapping("/onWork")
  public String onWork() {
    TimeCard.onWork(getAccount());
    return "forward:/timeCard";
  }


  @RequestMapping("/leave")
  public String leave() {
    TimeCard.leave(getAccount());
    return "forward:/timeCard";
  }

  @RequestMapping("/report")
  public @ResponseBody AttendanceReport workReport() {
    return AttendanceReport.MonthlyReport(AccountUtil.getCurrentUser(), LocalDate.now());
  }

  private Account getAccount() {
    CrudRepository<Account, Long> repository = CrudService.<Account, Long>createRepository(Account.class);
    return repository.findOne(1L);
  }

}
