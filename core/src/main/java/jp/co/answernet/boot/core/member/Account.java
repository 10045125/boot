package jp.co.answernet.boot.core.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yasuhiro on 2014/05/03.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  @NotNull
  Integer employeeId;

  @Column
  @NotNull
  String name;

  @Column
  @NotNull
  Integer age;

  @Column
  @NotNull
  Date employmentStartDate;

  @Column
  Date employmentEndDate;


}
