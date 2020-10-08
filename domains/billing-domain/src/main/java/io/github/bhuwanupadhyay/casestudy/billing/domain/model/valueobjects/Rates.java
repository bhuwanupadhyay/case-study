package io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Rates(List<ItemPriceInfo> value) {
  public Optional<Price> getByItemId(ItemId itemId) {
    return this.value.stream().filter(e -> Objects.equals(e.itemId(), itemId))
        .findFirst()
        .map(ItemPriceInfo::price);
  }
}
