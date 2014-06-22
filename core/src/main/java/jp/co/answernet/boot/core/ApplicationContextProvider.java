package jp.co.answernet.boot.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by yasuhiro on 2014/02/19.
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

  private static ApplicationContext context;


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return context;
  }


  @SuppressWarnings({"unchecked"})
  public static<T> T getBean( String name ) {
    return (T)getApplicationContext().getBean(name);
  }

  public static <T> T createBean( Class<T> clazz ) {
    return getApplicationContext().getAutowireCapableBeanFactory().createBean(clazz);
  }

  public static void autowire( Object bean ) {
    getApplicationContext().getAutowireCapableBeanFactory().autowireBean(bean);
  }

}
