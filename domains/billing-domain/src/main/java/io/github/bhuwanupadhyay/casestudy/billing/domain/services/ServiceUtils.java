package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemPriceInfo;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class ServiceUtils {

  static Rates getRates(InventoryService inventoryService, List<OrderItem> orderItems) {
    Set<ItemId> itemIds =
        orderItems.stream().map(OrderItem::itemId).map(ItemId::new).collect(toSet());

    List<CompletableFuture<ItemPriceInfo>> futures = itemIds.stream()
        .map(inventoryService::getItemPrice)
        .collect(toList());

    CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

    return new Rates(futures.stream().map(ServiceUtils::getPrice).collect(toList()));
  }

  static ItemPriceInfo getPrice(CompletableFuture<ItemPriceInfo> future) {
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new IllegalStateException(e);
    }
  }
}
