package jp.co.answernet.boot.core.team;


import jp.co.answernet.boot.core.member.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.util.Date;

/**
 * Created by yasuhiro on 2014/05/20.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToOne
  @NaturalId
  Team team;

  @OneToOne
  @NaturalId
  Account account;

  @OneToOne
  TeamRole role;

  @Column
  Date validStart;

  @Column
  Date validEnd;
}
