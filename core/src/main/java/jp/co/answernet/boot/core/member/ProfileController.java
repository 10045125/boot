package jp.co.answernet.boot.core.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yasuhiro on 2014/05/04.
 */
@Controller
@RequestMapping("profile")
public class ProfileController {

  @RequestMapping(method = RequestMethod.GET)
  public String index() {
    return "profile/profile";
  }

}
