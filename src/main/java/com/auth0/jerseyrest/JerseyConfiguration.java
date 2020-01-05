package com.auth0.jerseyrest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        packages("com.auth0.jerseyrest.resources");
    }
}
