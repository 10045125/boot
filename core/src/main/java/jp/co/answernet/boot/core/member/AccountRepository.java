package jp.co.answernet.boot.core.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yasuhiro on 2014/06/07.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
