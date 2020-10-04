package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto.ItemDto;

public class ItemResource {

  private String itemName;
  private String itemDescription;

  public ItemResource() {
  }

  public ItemResource(ItemDto itemDto) {
    this.itemName = itemDto.getItemName();
    this.itemDescription = itemDto.getItemDescription();
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public String getItemName() {
    return itemName;
  }
}