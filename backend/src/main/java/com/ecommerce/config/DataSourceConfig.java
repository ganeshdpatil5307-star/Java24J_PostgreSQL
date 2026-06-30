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
        if (url != null && url.startsWith("jdbc:mysql")) {
            // MySQL 8 requires these properties — without them connection fails with
            // "Public key retrieval is not allowed" on caching_sha2_password auth
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            ds.addDataSourceProperty("allowPublicKeyRetrieval", "true");
            ds.addDataSourceProperty("useSSL", "false");
            ds.addDataSourceProperty("serverTimezone", "UTC");
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
