package io.github.bhuwanupadhyay.casestudy.inventory.domain;

import java.util.Objects;

public class InventoryItem {

  private ItemId itemId;
  private String itemName;
  private String itemDescription;
  private Price price;
  private StockQuantity stockQuantity;

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InventoryItem that = (InventoryItem) o;
    return Objects.equals(itemId, that.itemId);
  }

  @Override public int hashCode() {
    return Objects.hash(itemId);
  }

  public ItemId getItemId() {
    return itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public Price getPrice() {
    return price;
  }

  public StockQuantity getStockQuantity() {
    return stockQuantity;
  }
}
