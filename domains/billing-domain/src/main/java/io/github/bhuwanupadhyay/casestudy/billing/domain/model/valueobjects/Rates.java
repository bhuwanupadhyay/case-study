package io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects;

import java.util.Map;

public record Rates(Map<ItemId, Price> value) {
  public Price getByItemId(ItemId itemId) {
    return this.value.get(itemId);
  }
}
