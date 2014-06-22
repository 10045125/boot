package jp.co.answernet.boot.core.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by yasuhiro on 2014/05/03.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  Long id;

  @Column
  String name;

  @Column
  String description;

  /**
   * TODO create customer type
   */
  @Column
  String customer;

  @Column
  String phase;

  /**
   * TODO create project method type.
   */
  @Column
  String method;

  @Column
  Date start;

  @Column
  Date end;

}
