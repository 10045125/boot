package jp.co.answernet.boot.core.team;

import jp.co.answernet.boot.core.member.Account;
import jp.co.answernet.boot.core.util.AccountUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by yasuhiro on 2014/06/14.
 */
@Controller
@RequestMapping("/team")
public class TeamController {

  @RequestMapping({"", "/"})
  public String index( Map<String, Object> model ) {
    // TODO return user joined teams.
    model.put("teams", Team.list());
    model.put("joined", Team.getJoinedTeams(AccountUtil.getCurrentUser()));
    return "team/index";
  }

  @RequestMapping("/join")
  public String join( @RequestParam Long teamId, Map<String, Object> model ) {

    return "team/index";
  }

  @RequestMapping("/list")
  public String list( @RequestParam boolean joinable, Map<String, Object> model ) {

    return "team/index";
  }
}
