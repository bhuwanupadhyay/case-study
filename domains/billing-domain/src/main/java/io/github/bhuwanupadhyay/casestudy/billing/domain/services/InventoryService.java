package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemInfo;
import java.util.concurrent.CompletableFuture;

public interface InventoryService {

  CompletableFuture<ItemInfo> getItemPrice(ItemId itemId);
}
