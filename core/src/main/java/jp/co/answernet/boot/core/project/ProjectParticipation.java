package jp.co.answernet.boot.core.project;

import jp.co.answernet.boot.core.member.Account;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created by yasuhiro on 2014/05/03.
 */
@Entity
@Data
public class ProjectParticipation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToOne
  Project project;

  @OneToOne
  Account account;

  @Column
  ProjectRole role;

  @Column
  String phase;

  @Column
  Date start;

  @Column
  Date end;

}
