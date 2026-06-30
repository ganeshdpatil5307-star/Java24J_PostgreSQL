package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@RestController
@RequestMapping("/api/db")
public class DbStatusController {

    private final DataSource dataSource;

    @Value("${spring.datasource.url:jdbc:h2:mem:ecommercedb}")
    private String datasourceUrl;

    public DbStatusController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/status")
    public Map<String, Object> status() {
        boolean connected = false;
        String error = null;

        try (Connection conn = dataSource.getConnection()) {
            connected = conn.isValid(2);
        } catch (Exception ex) {
            error = ex.getMessage();
        }

        boolean usingCloud = datasourceUrl != null
                && !datasourceUrl.startsWith("jdbc:h2");

        return Map.of(
                "connected", connected,
                "mode", usingCloud ? "CylonCloud MySQL" : "H2 (in-memory / local dev)",
                "datasourceUrl", maskPassword(datasourceUrl),
                "error", error != null ? error : ""
        );
    }

    private String maskPassword(String url) {
        if (url == null) return "";
        // mask anything after the last @ to hide credentials in the URL
        return url.replaceAll(":[^:@/]+@", ":****@");
    }
}
