package jp.co.answernet.boot.core.util;

import jp.co.answernet.boot.core.ApplicationContextProvider;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yasuhiro on 2014/06/05.
 */
@Service
public class CrudService {

  private static final String ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME = "entityManagerFactory";

  private static final Map<SimpleJpaRepository, EntityTransaction> txMap = new ConcurrentHashMap<>();

  @SuppressWarnings("unchecked")
  public static <T, ID extends Serializable> SimpleJpaRepository<T, ID> createRepository( Class<T> entityClass ) {
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    SimpleJpaRepository<T, ID> result = new SimpleJpaRepository<T, ID>(entityClass, em);
    txMap.put(result, tx);
    tx.begin();
    return result;
  }

  public static void commit( SimpleJpaRepository repository ) {
    txMap.get(repository).commit();
  }

  private static EntityManager getEntityManager() {
    EntityManagerFactory emf = ApplicationContextProvider.<EntityManagerFactory>getBean(ENTITY_MANAGER_FACTORY_BUILDER_BEAN_NAME);
    return emf.createEntityManager();
  }

}
