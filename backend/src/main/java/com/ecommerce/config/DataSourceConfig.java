package com.ecommerce.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
    public class DataSourceConfig {

@Value("${spring.datasource.url}")
        private String url;

@Value("${spring.datasource.username:sa}")
        private String username;

@Value("${spring.datasource.password:}")
        private String password;

@Bean
        @Primary
        public DataSource dataSource() {
            if (url != null && url.startsWith("jdbc:postgresql")) {
                // PostgreSQL — a standard Hikari pool is sufficient. Unlike MySQL,
            // PostgreSQL does not require allowPublicKeyRetrieval / serverTimezone.
            HikariDataSource ds = new HikariDataSource();
                ds.setDriverClassName("org.postgresql.Driver");
                ds.setJdbcUrl(url);
                ds.setUsername(username);
                ds.setPassword(password);
                return ds;
            }

        // H2 or any other driver — let Spring Boot handle it normally
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .build();
        }
    }
