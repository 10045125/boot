package jp.co.answernet.boot.core.member;

import jp.co.answernet.boot.core.project.ProjectParticipation;
import jp.co.answernet.boot.core.util.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by yasuhiro on 2014/05/22.
 */
@Controller
public class MyPageController {

  @RequestMapping("/mypage")
  public String index( Map<String, Object> model ) {
//    model.put("project", ProjectParticipation.getCurrentProject(null));
//    model.put("workstyle", WorkStyle.currentWorkStyle(null));
    return "mypage/index";
  }


}
