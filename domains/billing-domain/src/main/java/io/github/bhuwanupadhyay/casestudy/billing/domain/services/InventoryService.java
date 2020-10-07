package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Price;

import java.util.Map;
import java.util.Set;

public interface InventoryService {

    Map<ItemId, Price> getItemRates(Set<ItemId> itemIds);
}
