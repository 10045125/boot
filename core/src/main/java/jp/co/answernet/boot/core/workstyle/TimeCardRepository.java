package jp.co.answernet.boot.core.workstyle;

import jp.co.answernet.boot.core.workstyle.TimeCard;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by yasuhiro on 2014/06/05.
 */
@Repository
public interface TimeCardRepository extends CrudRepository<TimeCard, Long> {}
