package com.evgen.ua.dao;

import com.evgen.ua.entity.Item;
import com.evgen.ua.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.evgen.ua.constants.Queries.*;

@Service
public class ItemDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private Mapper mapper;

  public void save(Item item) {
    jdbcTemplate.update(INSERT_ITEM, item.getId(), item.getName(), item.getDescription(), item.getCreated());
  }

  public void update(Item item) {
    jdbcTemplate.update(UPDATE_ITEM, item.getName(), item.getDescription(), item.getId());
  }

  public void delete(String id) {
    jdbcTemplate.update(DELETE_ITEM, id);
  }

  public List<Item> loadItems() {
    return jdbcTemplate.query(LOAD_ITEM, mapper::mapItem);
  }
}
