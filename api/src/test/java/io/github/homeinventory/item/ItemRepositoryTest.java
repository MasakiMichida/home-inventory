package io.github.homeinventory.item;

import io.github.homeinventory.dto.ItemRequestDto;
import io.github.homeinventory.repository.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemRepositoryTest {
    
    @Container
    @ServiceConnection
    static final PostgreSQLContainer<?> postgres = 
    new PostgreSQLContainer<>("postgres:16-alpine")
    .withDatabaseName("home-inventory")
    .withUsername("api")
    .withPassword("api");

    @Autowired
    ItemRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanup(){
        jdbcTemplate.execute("TRUNCATE TABLE items RESTART IDENTITY");
    }

    @Test
    void insert_正常系_DBに1行入る(){
        var req = new ItemRequestDto("ringo", 100);
        int rows = repository.insert(req);

        assertThat(rows).isEqualTo(1);

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM items WHERE name = ? AND quantity = ?",
            Integer.class,
            "ringo",100
        );

        assertThat(count).isEqualTo(1);
    }

}