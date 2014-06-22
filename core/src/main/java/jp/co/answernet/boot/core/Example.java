package jp.co.answernet.boot.core;

import lombok.*;

import javax.persistence.*;

/**
 * Created by yasuhiro on 2014/05/27.
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;
}
