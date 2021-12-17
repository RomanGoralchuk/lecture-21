package by.itacademy.javaenterprise.goralchuk.config;

import by.itacademy.javaenterprise.goralchuk.spring.PersistenceConfig;
import by.itacademy.javaenterprise.goralchuk.spring.ServicesConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                PersistenceConfig.class, ServicesConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
