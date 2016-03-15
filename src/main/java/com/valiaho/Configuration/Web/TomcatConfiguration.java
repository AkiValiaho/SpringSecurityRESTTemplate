package com.valiaho.Configuration.Web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by akivv on 26.2.2016.
 */
@Configuration
public class TomcatConfiguration {
    /**
     * {@link https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html}
     * This class needs to be a separated config file, because
     * Spring loads WebSecurityConfigurerAdapter very early and it doesn't contain
     * a ServletContext
     */
    /**
     * Returns a new {@link EmbeddedServletContainerFactory}
     * This instance redirects HTTP to HTTPS
     *
     * @return {@link EmbeddedServletContainerFactory}
     */
    //Bean will override original EmbeddedServletContainerFactory
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                //If securityConstraint is set to CONFIDENTIAL
                //SSL is an assumption.
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    /**
     * Specifies the connector entity that can be used
     * with {@link TomcatEmbeddedServletContainerFactory}
     * Used to redirect all http traffic to secure HTTPS-port.
     *
     * @return {@link Connector}
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);

        return connector;
    }
}
