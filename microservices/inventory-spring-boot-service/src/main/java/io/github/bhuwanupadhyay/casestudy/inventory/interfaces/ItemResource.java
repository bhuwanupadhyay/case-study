package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto.ItemDto;
import java.math.BigDecimal;

public class ItemResource {

  private String itemName;
  private String itemDescription;
  private BigDecimal price;
  private Integer quantity;

  public ItemResource() {
  }

  public ItemResource(ItemDto itemDto) {
    this.itemName = itemDto.getItemName();
    this.itemDescription = itemDto.getItemDescription();
    this.price = itemDto.getPrice();
    this.quantity = itemDto.getQuantity();
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public String getItemName() {
    return itemName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "ItemResource{" +
        "itemName='" + itemName + '\'' +
        ", itemDescription='" + itemDescription + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}