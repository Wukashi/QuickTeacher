package pl.coderslab.QuickTeacher;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "pl.coderslab")
@EnableJpaRepositories("pl.coderslab.QuickTeacher.repository")
@EnableTransactionManagement
public class AppConfig {

}