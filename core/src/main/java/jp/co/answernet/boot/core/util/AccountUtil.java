package jp.co.answernet.boot.core.util;

import jp.co.answernet.boot.core.member.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yasuhiro on 2014/06/17.
 */
public class AccountUtil {

  public static Account getCurrentUser() {
    CrudRepository<Account, Long> repository = CrudService.<Account, Long>createRepository(Account.class);
    return repository.findOne(1L);
  }
}
