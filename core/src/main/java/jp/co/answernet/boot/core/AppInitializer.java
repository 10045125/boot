package jp.co.answernet.boot.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by yasuhiro on 2014/05/28.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy
@EnableJpaRepositories
public class AppInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AppInitializer.class);
    DataInitializer.initialize();
  }
}
