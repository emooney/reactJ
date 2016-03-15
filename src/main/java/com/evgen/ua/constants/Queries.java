package com.evgen.ua.constants;

public interface Queries {

  String INSERT_ITEM = "INSERT INTO items(id, name, description, created) VALUES(?,?,?,?);";
  String UPDATE_ITEM = "UPDATE items SET name=?, description=? WHERE id=?;";
  String DELETE_ITEM = "DELETE FROM items WHERE id=?;";
  String LOAD_ITEM = "SELECT*FROM items ORDER BY created;";
}
