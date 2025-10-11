package spring.fundamentals.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:postgresql://neondb_owner:npg_yqnwPxQXMu18@ep-frosty-glitter-adqvrjka-pooler.c-2.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require");
        driverManagerDataSource.setPassword("npg_yqnwPxQXMu18");
        driverManagerDataSource.setUsername("neondb_owner");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }

    @Bean("customTransactionManager")
    public PlatformTransactionManager getTransactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    // for approach 2 we need to create transaction template
    @Bean
    public TransactionTemplate getTransactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

}
