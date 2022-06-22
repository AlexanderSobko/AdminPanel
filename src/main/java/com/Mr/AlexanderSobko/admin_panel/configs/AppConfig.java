package com.Mr.AlexanderSobko.admin_panel.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {

    @Value("${DB_URL}")
    String dbURL;
    @Value("${DB_USERNAME}")
    String dbUserName;
    @Value("${DB_PASSWORD}")
    String dbPassword;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbURL);
        dataSource.setPassword(dbPassword);
        dataSource.setUsername(dbUserName);
        return dataSource;
    }
}
