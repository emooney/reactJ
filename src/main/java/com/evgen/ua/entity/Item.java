package com.evgen.ua.entity;

import java.util.Date;

public class Item {

  private String id;

  private String name;

  private String description;

  private Date created;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Item item = (Item) o;

    if (id != null ? !id.equals(item.id) : item.id != null)
      return false;
    if (name != null ? !name.equals(item.name) : item.name != null)
      return false;
    if (description != null ? !description.equals(item.description) : item.description != null)
      return false;
    return !(created != null ? !created.equals(item.created) : item.created != null);

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (created != null ? created.hashCode() : 0);
    return result;
  }
}
