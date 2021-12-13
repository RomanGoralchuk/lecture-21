package by.itacademy.javaenterprise.goralchuk.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "by.itacademy.javaenterprise.goralchuk.dao")
@PropertySource("classpath*:*.properties")
@EnableTransactionManagement
public class PersistenceConfig {
    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "close")
    public DataSource dataSourceBean(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("db.driver.class"));
        basicDataSource.setUrl(environment.getProperty("db.url"));
        basicDataSource.setUsername(environment.getProperty("db.username"));
        basicDataSource.setPassword(environment.getProperty("db.password"));
        basicDataSource.setMaxTotal(environment.getProperty("db.maxTotal", Integer.class));
        return basicDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSourceBean());
        entityManagerFactory.setPackagesToScan("by.itacademy.javaenterprise.goralchuk.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getProperty("hibernate.show.sql"));
        jpaProperties.put("hibernate.connection.autocommit", environment.getProperty("hibernate.show.sql"));
        jpaProperties.put("hibernate.show_sql", environment.getProperty("hhibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", environment.getProperty("ibernate.format_sql"));
        jpaProperties.put("hibernate.physical_naming_strategy", environment.getProperty("ibernate.physical_naming_strategy"));
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    protected TransactionTemplate transactionTemplate() {
        var transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        transactionTemplate.setTimeout(environment.getProperty("spring.transaction.timeout", Integer.class));
        return transactionTemplate;
    }
}
