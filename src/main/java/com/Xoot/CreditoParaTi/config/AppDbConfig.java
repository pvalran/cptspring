package com.Xoot.CreditoParaTi.config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "AppEntityManagerFactory",
        transactionManagerRef = "AppTransactionManager",
        basePackages = { "com.Xoot.CreditoParaTi.repositories.app" }
)
public class AppDbConfig {
    @Primary
    @Bean(name = "AppDataSource")
    @ConfigurationProperties(prefix = "cptapp.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "AppEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("AppDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.Xoot.CreditoParaTi.entity.app")
                .persistenceUnit("cptapp")
                .build();
    }

    @Primary
    @Bean(name = "AppTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("AppEntityManagerFactory") EntityManagerFactory
                    AppEntityManagerFactory
    ) {
        return new JpaTransactionManager(AppEntityManagerFactory);
    }
}
