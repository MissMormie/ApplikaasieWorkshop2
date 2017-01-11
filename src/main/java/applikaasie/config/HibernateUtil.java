/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author Sonja
 */

//@EnableJpaRepositories("applikaasie")
public class HibernateUtil {
  // DB Variables
  private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD = "kaaskop";
  private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost/Applikaasie";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "boerpiet";
  
  // Hibernate Variables
  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
  private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
  
  
  
  /*
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
    emfb.setDataSource(dataSource);
    emfb.setJpaVendorAdapter(jpaVendorAdapter);
    emfb.setPackagesToScan("applikaasie");
    return emfb;
  }
  
  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    // TODO find how i can make this configurable through the properties
    adapter.setDatabase(Database.MYSQL);
    adapter.setShowSql(true);
    adapter.setGenerateDdl(true);
    adapter.setDatabasePlatform(PROPERTY_NAME_HIBERNATE_DIALECT);
    return adapter;
  }  */

}
