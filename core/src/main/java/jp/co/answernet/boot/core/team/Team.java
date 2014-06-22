package jp.co.answernet.boot.core.team;


import jp.co.answernet.boot.core.member.Account;
import jp.co.answernet.boot.core.util.CrudService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yasuhiro on 2014/05/03.
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  String name;

  @Column
  String description;

  @OneToMany(fetch = FetchType.EAGER)
  List<TeamMember> teamMembers;

  @OneToMany(fetch = FetchType.EAGER)
  List<Team> subTeams = new ArrayList<>();


  public List<TeamMember> getTeamMembers() {
    return Collections.unmodifiableList(_getMembersIfAbsent());
  }

  public void addTeamMember( TeamMember teamMember ) {
    this._getMembersIfAbsent().add(teamMember);
  }

  public void removeTeamMember( TeamMember teamMember ) {
    this._getMembersIfAbsent().remove(teamMember);
  }

  private synchronized List<TeamMember> _getMembersIfAbsent() {
    if ( this.teamMembers == null ) {
      this.teamMembers = new ArrayList<>();
    }
    return this.teamMembers;
  }

  public List<Team> getSubTeams() {
    return Collections.unmodifiableList(subTeams);
  }

  public void addSubTeam( Team team ) {
    this.subTeams.add(team);
  }

  public void removeSubTeam( Team team ) {
    this.subTeams.remove(team);
  }


  public static List<Team> list() {
    SimpleJpaRepository<Team, Serializable> repository = CrudService.createRepository(Team.class);
    return repository.findAll();
  }

  public List<String> getMemberNames() {
    return teamMembers.stream().map(( tm ) -> {return tm.getAccount().getName();}).collect(Collectors.toList());
  }


  public static List<Team> getJoinedTeams( final Account user ) {
    SimpleJpaRepository<Team, Serializable> repository = CrudService.createRepository(Team.class);
    return repository.findAll(( Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder cb ) -> {
      return cb.equal(root.join("teamMembers").get("account"), user);
    });
  }

}
