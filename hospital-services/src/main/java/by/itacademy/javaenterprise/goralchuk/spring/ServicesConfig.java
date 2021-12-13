package by.itacademy.javaenterprise.goralchuk.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath*:*.properties")
@ComponentScan(basePackages = "by.itacademy.javaenterprise.goralchuk.services")
public class ServicesConfig {
}
