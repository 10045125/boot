package jp.co.answernet.boot.core;

import jp.co.answernet.boot.core.member.Account;
import jp.co.answernet.boot.core.project.Project;
import jp.co.answernet.boot.core.team.Team;
import jp.co.answernet.boot.core.team.TeamMember;
import jp.co.answernet.boot.core.team.TeamRole;
import jp.co.answernet.boot.core.util.CrudService;
import jp.co.answernet.boot.core.util.TimeUtil;
import jp.co.answernet.boot.core.workstyle.TimeCard;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yasuhiro on 2014/06/16.
 */
public class DataInitializer {

  public static void initialize() {

    // Create Account
    ArrayList<Account> accounts = new ArrayList<>();
    accounts.add(new Account(null, 1, "Shinji Kagawa", 24, new Date(), null));
    accounts.add(new Account(null, 2, "Keisuke Honda", 24, new Date(), null));
    accounts.add(new Account(null, 3, "Yuto Nagatomo", 24, new Date(), null));
    accounts.add(new Account(null, 4, "Keigo Kawashima", 24, new Date(), null));
    accounts.add(new Account(null, 5, "Kazu Miura", 24, new Date(), null));

    SimpleJpaRepository<Account, Serializable> accountRepository = CrudService.createRepository(Account.class);
    accountRepository.save(accounts);
    CrudService.commit(accountRepository);

    // Create Team
    ArrayList<Team> teams = new ArrayList<>();
    teams.add(new Team(null, "Japan National", "Japan National Football Team", null, null));
    SimpleJpaRepository<Team, Serializable> teamRepository = CrudService.createRepository(Team.class);
    teamRepository.save(teams);
    CrudService.commit(teamRepository);

    // Create Roles
    ArrayList<TeamRole> roles = new ArrayList<>();
    roles.add(new TeamRole(null, teams.get(0), "LEADER"));
    roles.add(new TeamRole(null, teams.get(0), "STAFF"));

    SimpleJpaRepository<TeamRole, Serializable> teamRoleRepository = CrudService.createRepository(TeamRole.class);
    teamRoleRepository.save(roles);
    CrudService.commit(teamRoleRepository);

    // Create Members
    ArrayList<TeamMember> members = new ArrayList<>();
    members.add(new TeamMember(null, teams.get(0), accounts.get(0), roles.get(0), new Date(), null));
    members.add(new TeamMember(null, teams.get(0), accounts.get(1), roles.get(1), new Date(), null));
    members.add(new TeamMember(null, teams.get(0), accounts.get(2), roles.get(0), new Date(), null));
    members.add(new TeamMember(null, teams.get(0), accounts.get(3), roles.get(0), new Date(), null));
    members.add(new TeamMember(null, teams.get(0), accounts.get(4), roles.get(0), new Date(), null));

    SimpleJpaRepository<TeamMember, Serializable> teamMemberRepository = CrudService.createRepository(TeamMember.class);
    teamMemberRepository.save(members);
    CrudService.commit(teamMemberRepository);

    // Update Team (add Members)
    members.forEach((member) ->{ teams.get(0).addTeamMember(member);});
    teamRepository = CrudService.createRepository(Team.class);
    teamRepository.save(teams);
    CrudService.commit(teamRepository);

    // Create Project
    ArrayList<Project> projects = new ArrayList<>();
    projects.add(new Project(null, "きついプロジェクト", "ブラックサービス興業", "えせagile", "炎上PJ", "RP", new Date(), null));
    SimpleJpaRepository<Project, Serializable> projectRepository = CrudService.createRepository(Project.class);
    projectRepository.save(projects);
    CrudService.commit(projectRepository);

    // Create TimeCard
    ArrayList<TimeCard> timeCards = new ArrayList<>();
    LocalDateTime current = LocalDateTime.now().withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS).plusHours(8).plusMinutes(30);
    while (current.isBefore(LocalDateTime.now()) ) {
      if (TimeUtil.isWeekDay(current)) {
        timeCards.add(new TimeCard(null, accounts.get(0), TimeUtil.toTimestamp(current), TimeUtil.toTimestamp(current.plusMinutes(510)), null));
      }
        current = current.plusDays(1);
    }
    SimpleJpaRepository<TimeCard, Serializable> timeCardRepository = CrudService.createRepository(TimeCard.class);
    timeCardRepository.save(timeCards);
    CrudService.commit(timeCardRepository);
  }
}
