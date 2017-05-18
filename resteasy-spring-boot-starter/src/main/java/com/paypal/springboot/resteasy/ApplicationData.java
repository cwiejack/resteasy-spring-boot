package com.paypal.springboot.resteasy;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationData {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationData.class);

    private Application application;
    private Set<Class<?>> resources;
    private Set<Class<?>> providers;

    public ApplicationData(final Application application) {
        this.application = application;
        resources = new HashSet<Class<?>>();
        providers = new HashSet<Class<?>>();
    }

    public ApplicationData(final Class<? extends Application> application) {
        try {
            this.application = application.newInstance();
        } catch (Exception e) {
            String exceptionMessage = String.format("JAX-RS Application class %s could not been instantiated", application.getCanonicalName());
            logger.error(exceptionMessage, e);
            throw new BeansException(exceptionMessage, e){};
        }
        resources = new HashSet<Class<?>>();
        providers = new HashSet<Class<?>>();
    }

    public Class<? extends Application> getApplicationClass() {
        return application.getClass();
    }

    public Application getApplication() {
        return application;
    }
    /**
     *
     * @param candidate
     * @return true if candidate added to the application, false otherwise
     */
    public boolean applyResource(Class<?> candidate) {
        if (hasClassesConfigured()) {
            if (application.getClasses().contains(candidate)) {
                resources.add(candidate);
                return true;
            } else {
                return false;
            }
        }
        resources.add(candidate);
        return true;
    }

    public Set<Class<?>> getResources() {
        return Collections.unmodifiableSet(resources);
    }

    public Set<Class<?>> getProviders() {
        return Collections.unmodifiableSet(providers);
    }

    private boolean hasClassesConfigured() {
        return application.getClasses() != null && application.getClasses().size() > 0;
    }
}
