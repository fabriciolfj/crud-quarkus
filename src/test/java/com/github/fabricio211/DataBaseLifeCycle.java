package com.github.fabricio211;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class DataBaseLifeCycle implements QuarkusTestResourceLifecycleManager {

    private static MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql/mysql-server:5.7.30");

    @Override
    public Map<String, String> start() {
        MYSQL.start();
        Map<String, String> propriedades = new HashMap<>();
        propriedades.put("quarkus.datasource.url", MYSQL.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", MYSQL.getUsername());
        propriedades.put("quarkus.datasource.password", MYSQL.getPassword());
        return propriedades;
    }

    @Override
    public void stop() {
        if(MYSQL != null) {
            MYSQL.stop();
        }
    }
}