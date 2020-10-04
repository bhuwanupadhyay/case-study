package io.github.bhuwanupadhyay.casestudy.inventory.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Inventory {

  private InventoryId inventoryId;
  private String inventoryName;
  private final Set<InventoryItem> inventoryItems = new HashSet<>();

  public InventoryId getInventoryId() {
    return inventoryId;
  }

  public String getInventoryName() {
    return inventoryName;
  }

  public Set<InventoryItem> getInventoryItems() {
    return inventoryItems;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Inventory inventory = (Inventory) o;
    return Objects.equals(inventoryId, inventory.inventoryId);
  }

  @Override public int hashCode() {
    return Objects.hash(inventoryId);
  }
}
