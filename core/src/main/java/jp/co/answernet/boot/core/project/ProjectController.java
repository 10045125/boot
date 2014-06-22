package jp.co.answernet.boot.core.project;

        import jp.co.answernet.boot.core.util.CrudService;
        import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;

        import java.util.Map;

/**
 * Created by yasuhiro on 2014/05/10.
 */
@Controller
public class ProjectController {

  @RequestMapping("/projects")
  public String list( Map<String, Object> model ) {
    Iterable<Project> list = getCrudRepository().findAll();
    model.put("projects", list);
    return "project/index";
  }


  @RequestMapping("/project/show")
  public String project(@RequestParam Long id, Map<String, Object> model ) {
    model.put("project", getCrudRepository().findOne(id));
    return "project/project";
  }


  private SimpleJpaRepository<Project, Long> getCrudRepository() {
    return CrudService.<Project, Long>createRepository(Project.class);
  }


}

