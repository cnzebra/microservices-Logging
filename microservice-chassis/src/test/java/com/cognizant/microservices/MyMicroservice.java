package com.cognizant.microservices;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Module;
import com.google.inject.Scopes;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

public class MyMicroservice extends Microservice {

    public static void main(String... args) {
        new MyMicroservice().run();
    }

    @Override
    public Module[] getModules() {
        return new Module[] {
            new RequestScopeModule() {

                @Override
                protected void configure()
                {
                    bind(DataResource.class).in(Scopes.SINGLETON);
                }
            }
        };
    }

    @Path("/")
    public static class DataResource {

        private static final Logger logger =
                LoggerFactory.getLogger(DataResource.class);

        @GET
        @Produces("application/json")
        public Map<String, Boolean> getData() {
            logger.info("Getting some data");
            return ImmutableMap.of("value", true);
        }
    }
}
