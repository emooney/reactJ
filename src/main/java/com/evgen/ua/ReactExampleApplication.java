package com.evgen.ua;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
public class ReactExampleApplication extends WebMvcConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) {
    SpringApplication.run(ReactExampleApplication.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/item").setViewName("item");
  }

  @Bean
  public SpringLiquibase liquibase() {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:/liquibase/changelog.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }
}
