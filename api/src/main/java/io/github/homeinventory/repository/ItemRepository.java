package io.github.homeinventory.repository;

import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import io.github.homeinventory.dto.*;


@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(ItemRequestDto itemRequestDto){
        String sql = "INSERT INTO items (name,quantity) VALUES (?,?)";
        
        return jdbcTemplate.update(sql,itemRequestDto.name(),itemRequestDto.quantity());
    }

    public List<ItemResponseDto> findAll(){

        String sql = """
                SELECT id, name, quantity, created_at, updated_at
                FROM items
                ORDER BY updated_at DESC
                """;
        return jdbcTemplate.query(sql,(resultSet,rownum)-> new ItemResponseDto(
            resultSet.getInt("id"),
            resultSet.getString("name")
            ,resultSet.getInt("quantity")
            ,resultSet.getObject("created_at",java.time.OffsetDateTime.class)
            ,resultSet.getObject("updated_at",java.time.OffsetDateTime.class)
        ) );
    }
}
