package jp.co.answernet.boot.core;

import jp.co.answernet.boot.core.workstyle.TimeCard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by yasuhiro on 2014/05/28.
 */
@Controller
public class HelloController {

  @RequestMapping("/")
  public String index( Map<String, Object> model ) {
    return "index";
  }

  @RequestMapping("calendar")
  public String calendar() {
    return "calendar";
  }

}
