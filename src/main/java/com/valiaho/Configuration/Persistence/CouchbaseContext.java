package com.valiaho.Configuration.Persistence;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableCouchbaseRepositories("com.valiaho.DAO")
@Configuration
@PropertySource("classpath:dbProperties/db.properties")
class CouchBaseconfiguration extends AbstractCouchbaseConfiguration {
    @Autowired
    Environment environment;

    @Override
    protected List<String> getBootstrapHosts() {
        final List<String> collect = Stream.builder().add(environment
                .getRequiredProperty("db.nodes"))
                .build().map(String::valueOf)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    protected String getBucketName() {
        return environment.getRequiredProperty("db.bucket");
    }

    @Override
    protected String getBucketPassword() {
        return environment.getRequiredProperty("db.bucketPassword");
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingCouchbaseEventListener validationEventListener() {
        return new ValidatingCouchbaseEventListener(validator());
    }
}

