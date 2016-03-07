package com.valiaho.Configuration.Persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by akivv on 3.3.2016.
 */
@Configuration
@EnableTransactionManagement
//Enable JPA Repositories with EnableJpaRepositories annotation
//@EnableJpaRepositories-annotation has configuration options, because
//entityManagerFactory-beans are injected with default bean names like: "entityManagerFactory"
//and "transactionManager"
@EnableJpaRepositories(basePackages = {"com.valiaho.Repository"})
public class PersistenceContext {
    @Profile("default")
    @Configuration
    @PropertySource("classpath:dbProperties/db.properties")
    static class DefaultValues {}
    @Profile("dev")
    @Configuration
    @PropertySource({"classpath:dbProperties/db.properties", "classpath:dbProperties/dbDev.properties"})
    static class devDefaultValues {}
    @Autowired
    Environment env;

    /**
     * Sets environment properties to the hikaridatasource
     * from the autowired Spring environment and uses * classpath files. Destroy method is specified to be 'close'.
     * <p/>
     * At the time of writing this (04.03.2016) HikariCP is the fastest
     * JDBC Connection Pool: https://github.com/brettwooldridge/HikariCP
     *
     * @return returns a new {@link HikariDataSource} as a Spring based bean
     */
    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        hikariConfig.setPassword(env.getRequiredProperty("db.password"));
        hikariConfig.setUsername(env.getRequiredProperty("db.username"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        //Use Hibernate as a JpaVendor
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        //Inject the properties property to localContainerEntityManagerFactoryBean
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.valiaho.Domain");
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }
}
