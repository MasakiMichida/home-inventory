package io.github.homeinventory.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DbPingController {
    
    private final JdbcTemplate jdbcTemplate;

    public DbPingController(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/db/ping")
    public Map<String,Object> ping(){
        Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return Map.of("ok",true,"result",one);

    }
}
