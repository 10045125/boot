package jp.co.answernet.boot.core.member;


import jp.co.answernet.boot.core.team.Team;
import jp.co.answernet.boot.core.project.Project;
import jp.co.answernet.boot.core.values.Title;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created by yasuhiro on 2014/05/04.
 */
@Entity
@Data
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToOne
  Account account;

  @Column
  String sex;

  @Column
  Date birthDay;

  @OneToOne
  Team section;

  @Column
  Title title;

  @OneToOne
  Project currentProject;

  @Column
  String skills;

  @Column
  String qualifications;

}
