package com.evgen.ua.mapper;

import com.evgen.ua.entity.Item;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class Mapper {

  public Item mapItem(ResultSet rs, int rowNum) throws SQLException {
    Item item = new Item();
    item.setId(rs.getString("id"));
    item.setName(rs.getString("name"));
    item.setDescription(rs.getString("description"));
    return item;
  }
}
