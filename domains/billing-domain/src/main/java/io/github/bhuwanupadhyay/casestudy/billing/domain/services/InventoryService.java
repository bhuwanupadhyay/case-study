package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemPriceInfo;

import java.util.concurrent.CompletableFuture;

public interface InventoryService {

	CompletableFuture<ItemPriceInfo> getItemPrice(ItemId itemId);

}
