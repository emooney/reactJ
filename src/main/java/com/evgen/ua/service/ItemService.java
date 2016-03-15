package com.evgen.ua.service;

import com.evgen.ua.dao.ItemDAO;
import com.evgen.ua.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

  @Autowired
  private ItemDAO itemDAO;

  public void save(String name, String description) {
    Item item = new Item();
    item.setId(UUID.randomUUID().toString());
    item.setName(name);
    item.setDescription(description);
    item.setCreated(new Date());
    itemDAO.save(item);
  }

  public void update(Item item) {
    itemDAO.update(item);
  }

  public void delete(String id) {
    itemDAO.delete(id);
  }

  public List<Item> loadItems() {
    return itemDAO.loadItems();
  }
}
