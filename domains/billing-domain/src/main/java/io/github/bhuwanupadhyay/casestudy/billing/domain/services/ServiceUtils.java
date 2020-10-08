package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemInfo;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ServiceUtils {

  public static Rates getRates(InventoryService inventoryService, Map<String, Integer> orderItems) {
    Set<ItemId> itemIds = orderItems.keySet().stream().map(ItemId::new).collect(toSet());
    List<CompletableFuture<ItemInfo>> futures =
        itemIds.stream().map(inventoryService::getItemPrice).collect(toList());
    CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
    return new Rates(futures.stream().map(ServiceUtils::getPrice).collect(toList()));
  }

  private static ItemInfo getPrice(CompletableFuture<ItemInfo> future) {
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new IllegalStateException(e);
    }
  }
}
