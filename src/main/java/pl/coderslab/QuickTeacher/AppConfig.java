package pl.coderslab.QuickTeacher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "pl.coderslab")
@EnableJpaRepositories("pl.coderslab.QuickTeacher.repository")
@EnableTransactionManagement
public class AppConfig {

}