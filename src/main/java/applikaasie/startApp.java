/*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
 */
package applikaasie;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

@SpringBootApplication
@RestController
@Configuration
@ComponentScan("applikaasie")
@PropertySource("classpath:application.properties")
public class startApp {
  // DB Variables

  private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD = "kaaskop";
  private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost/Applikaasie";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "boerpiet";

  // Hibernate Variables
  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
  private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

  public static void main(String[] args) {
    SpringApplication.run(startApp.class, args);
  }

  @Bean
  public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine);
    return viewResolver;
  }

  @Bean
  public TemplateEngine templateEngine(TemplateResolver templateResolver) {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }

  @Bean
  public TemplateResolver templateResolver() {
    TemplateResolver templateResolver = new ServletContextTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/templates");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    return templateResolver;
  }

  @Bean
  public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
    return new PersistenceAnnotationBeanPostProcessor();
  }

  @Bean
  public BeanPostProcessor persistenceTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  /*
  @Bean
  @ConfigurationProperties()
  public DataSource dataSource(DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder()
            // additional customizations
            .build();
  }*/
  
  /*
  @Bean
  public DriverManagerDataSource dataSource(){
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
      ds.setUrl(PROPERTY_NAME_DATABASE_URL);
      ds.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
      ds.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
      return ds;
  }  
*/
}
