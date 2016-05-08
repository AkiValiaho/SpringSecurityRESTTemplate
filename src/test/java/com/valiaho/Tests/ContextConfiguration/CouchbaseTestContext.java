package com.valiaho.Tests.ContextConfiguration;

import com.valiaho.Service.ImageService;
import com.valiaho.Service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Collections;
import java.util.List;

/**
 * Created by akivv on 17.4.2016.
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableCouchbaseRepositories("com.valiaho.DAO")
public class CouchbaseTestContext extends AbstractCouchbaseConfiguration {
    @Autowired
    Environment environment;

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("localhost");
    }

    @Override
    protected String getBucketName() {
        return environment.getRequiredProperty("db.bucketName");
    }

    @Override
    protected String getBucketPassword() {
        return environment.getRequiredProperty("db.bucketPassword");
    }

    @Bean
    ImageService imageService() {
        return new ImageServiceImpl();
    }
}
