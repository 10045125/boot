package jp.co.answernet.boot.core.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by yasuhiro on 2014/05/21.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToOne
  @NaturalId
  Team team;

  @Column
  @NaturalId
  String role;

}
