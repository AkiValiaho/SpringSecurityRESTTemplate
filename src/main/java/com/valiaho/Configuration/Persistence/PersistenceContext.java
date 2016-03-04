package com.valiaho.Configuration.Persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by akivv on 3.3.2016.
 */
@Configuration
@PropertySource("classpath:dbProperties/db.properties")
@EnableTransactionManagement
//Enable JPA Repositories with EnableJpaRepositories annotation
@EnableJpaRepositories(basePackages = {"com.valiaho.Repository"})
public class PersistenceContext {
    @Autowired
    Environment env;

    /**
     * Sets environment properties to the hikaridatasource
     * from the autowired Spring environment and uses
     * classpath files. Destroy method is specified to be 'close'.
     * <p/>
     * At the time of writing this (04.03.2016) HikariCP is the fastest
     * JDBC Connection Pool: https://github.com/brettwooldridge/HikariCP
     *
     * @return returns a new {@link HikariDataSource} as a Spring based bean
     */
    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getRequiredProperty("db.driverclassname"));
        hikariConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        hikariConfig.setPassword("db.password");
        hikariConfig.setUsername("db.username");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        //Use Hibernate as a JpaVendor
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        //Inject the properties property to localContainerEntityManagerFactoryBean
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory((EntityManagerFactory) localContainerEntityManagerFactoryBean());
        return jpaTransactionManager;
    }
}
