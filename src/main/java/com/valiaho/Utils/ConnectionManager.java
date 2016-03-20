package com.valiaho.Utils;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@PropertySource(value = {
        "classpath:dbProperties/db.properties"})
public class ConnectionManager {
    private static final Logger logger = Logger.getLogger(
            ConnectionManager.class.getName());
    private static Cluster cluster;
    private static com.couchbase.client.java.Bucket mainBucket;
    private static com.couchbase.client.java.Bucket jobBucket;
    private static String OTHER_BUCKET_GW_BASE_URL;
    private static String JOB_BUCKET_GW_BASE_URL;
    private static CouchbaseEnvironment defaultEnv = DefaultCouchbaseEnvironment.create();
    @Autowired
    private Environment environment;

    public static com.couchbase.client.java.Bucket getMainInstance() {
        return mainBucket;
    }

    public static com.couchbase.client.java.Bucket getJobsInstance() {
        return jobBucket;
    }

    public static com.couchbase.client.java.Bucket getInstance(final DbBucket bucket) {
        if (bucket == DbBucket.MAIN) {
            return ConnectionManager.getMainInstance();
        }
        throw new RuntimeException(String.format("unknown bucket %s", bucket.toString()));
    }

    public static DbBucket getBucketFromString(final String strBucket) {
        if (strBucket.equals("other")) {
            return DbBucket.MAIN;
        }
        throw new IllegalArgumentException(String.format("unknown bucket type %s", strBucket));
    }

    public static String getGwUrl(final String bucketStr) {
        final DbBucket bucket = getBucketFromString(bucketStr);
        return getGwUrl(bucket);
    }

    public static String getGwUrl(final DbBucket bucket) {
        if (bucket == DbBucket.MAIN) {
            return OTHER_BUCKET_GW_BASE_URL;
        }
        throw new IllegalArgumentException(String.format("unknown bucket type %s", bucket.toString()));
    }

    @PostConstruct
    private void init() {
        /*SYNC GW Related Config*/
  /*      OTHER_BUCKET_GW_BASE_URL = environment.getRequiredProperty("syncGw.mainAdminUrl") + "/";
        JOB_BUCKET_GW_BASE_URL = environment.getRequiredProperty("syncGw.jobAdminUrl") + "/";*/

        final ConnectionConfiguration configuration = parseConfiguration();
        logger.log(Level.INFO, String.format("Connecting to Couchbase Cluster [%s]", configuration.nodes));
        CouchbaseEnvironment couchEnv = DefaultCouchbaseEnvironment.builder()
                .connectTimeout(configuration.connectTimeout)    //10000ms = 10s, default is 5s
                .viewTimeout(configuration.viewTimeout)
                .queryTimeout(configuration.queryTimeout)
                .autoreleaseAfter(configuration.autoReleaseAfter)
                .queryEnabled(true)
                .build();
        cluster = CouchbaseCluster.create(couchEnv, configuration.nodes.split(","));
        mainBucket = cluster.openBucket(configuration.bucket, configuration.bucketPassword);
        jobBucket = cluster.openBucket(configuration.jobBucket, "");
    }

    private ConnectionConfiguration parseConfiguration() {
        final ConnectionConfiguration configuration = new ConnectionConfiguration();
        configuration.nodes = environment.getRequiredProperty("db.nodes");
        validateConfigurationProperty(configuration.nodes, "Couchbase nodes not set");
        configuration.bucket = environment.getRequiredProperty("db.bucket");
        validateConfigurationProperty(configuration.bucket, "Couchbase bucket not set");
        configuration.bucketPassword = environment.getProperty("db.bucketPassword");
        configuration.jobBucket = environment.getRequiredProperty("db.jobBucket");
        validateConfigurationProperty(configuration.jobBucket, "Couchbase job bucket not set");

        AtomicBoolean propertyFound = new AtomicBoolean(false);
        long value = -1;
        value = parseLongProperty(environment, "db.connectTimeout", propertyFound);
        if (propertyFound.get()) {
            configuration.connectTimeout = value;
        }
        value = parseLongProperty(environment, "db.viewTimeout", propertyFound);
        if (propertyFound.get()) {
            configuration.viewTimeout = value;
        }
        value = parseLongProperty(environment, "db.queryTimeout", propertyFound);
        if (propertyFound.get()) {
            configuration.queryTimeout = value;
        }
        value = parseLongProperty(environment, "db.autoReleaseAfter", propertyFound);
        if (propertyFound.get()) {
            configuration.autoReleaseAfter = value;
        }
        logger.log(Level.INFO, String.format("Couchbase configuration parsed [%s]", configuration.toString()));
        return configuration;
    }

    private void validateConfigurationProperty(final String property, final String errorMessage) {
        if (property.isEmpty()) {
            throw new RuntimeException(errorMessage);
        }
    }

    private long parseLongProperty(final Environment environment, final String propertyName, AtomicBoolean propertyFound) {
        propertyFound.set(false);
        try {
            final String strProperty = environment.getProperty(propertyName);
            if (!strProperty.isEmpty()) {
                propertyFound.set(true);
                return Long.parseLong(strProperty);
            }
        } catch (Exception e) {
            propertyFound.set(false);
        }
        return -1;
    }

    @PreDestroy
    public void cleanup() {
        logger.log(Level.INFO, "Disconnecting from Couchbase Cluster");
        cluster.disconnect();
    }

    private class ConnectionConfiguration {
        public String nodes = "";
        public String bucket = "";
        public String bucketPassword = "";
        public String jobBucket = "";
        public Long connectTimeout = defaultEnv.connectTimeout();
        public Long viewTimeout = defaultEnv.viewTimeout();
        public Long queryTimeout = defaultEnv.queryTimeout();
        public Long autoReleaseAfter = defaultEnv.autoreleaseAfter();

        @Override
        public String toString() {
            return String.format("nodes: %s, bucket: %s, connectTimeout: %d, viewTimeout: %d, queryTimeout: %d, autoReleaseAfter: %d",
                    nodes, bucket, connectTimeout, viewTimeout, queryTimeout, autoReleaseAfter);
        }
    }
}