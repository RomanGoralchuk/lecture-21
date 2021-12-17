package by.itacademy.javaenterprise.goralchuk.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class SomeApplicationService {
    @Value("${application.name}")
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }
}
