package com.evgen.ua.controller;

import com.evgen.ua.entity.Item;
import com.evgen.ua.service.ItemService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.evgen.ua.constants.ProjectConstants.NAME_IS_EMPTY;
import static com.evgen.ua.util.SerializationUtil.buildEmptyRespose;
import static com.evgen.ua.util.SerializationUtil.buildErrorResponse;

@RestController
public class ItemController {

  @Autowired
  private ItemService itemService;

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(String name, String description){
    if(name == null || name.isEmpty()) {
      return buildErrorResponse(NAME_IS_EMPTY).toString();
    }
    itemService.save(name, description);
    return buildEmptyRespose(false).toString();
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Item item){
    if(item.getName() == null || item.getName().isEmpty()) {
      return buildErrorResponse(NAME_IS_EMPTY).toString();
    }
    itemService.update(item);
    return buildEmptyRespose(false).toString();
  }

  @RequestMapping(value = "/load", method = RequestMethod.GET)
  public String load(){
    return new JSONArray(itemService.loadItems()).toString();
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public String remove(String id){
    itemService.delete(id);
    return buildEmptyRespose(false).toString();
  }
}
