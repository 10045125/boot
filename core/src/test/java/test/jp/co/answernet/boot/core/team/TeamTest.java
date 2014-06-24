package test.jp.co.answernet.boot.core.team;

import jp.co.answernet.boot.core.team.Team;
import jp.co.answernet.boot.core.team.TeamMember;
import org.junit.Test;

import java.util.List;

/**
 * Created by yasuhiro on 2014/06/07.
 */
public class TeamTest {

  @Test(expected = UnsupportedOperationException.class)
  public void TeamGetMemberIsUnmodifiable() {
    Team sut = new Team();
    List<TeamMember> members = sut.getTeamMembers();
    sut.addTeamMember(new TeamMember()); // domain function is OK!
    assert members.size() == 1;
    members.add(new TeamMember());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void TeamGetSubTeamIsUnmodifiable() {
    Team sut = new Team();
    List<Team> subTeams = sut.getSubTeams();
    sut.addSubTeam(new Team()); // domain function is OK!
    assert subTeams.size() == 1;
    subTeams.add(new Team());
  }

}
