package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import io.github.bhuwanupadhyay.casestudy.inventory.application.queryservices.InventoriesQueryService;
import io.github.bhuwanupadhyay.casestudy.inventory.domain.ItemId;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryHttpHandler implements HttpHandler {

  private final InventoriesQueryService inventoriesQueryService;

  public InventoryHttpHandler(InventoriesQueryService inventoriesQueryService) {
    this.inventoriesQueryService = inventoriesQueryService;
  }

  @Override
  public List<ItemResource> getItems() {
    return inventoriesQueryService.selectItems();
  }

  @Override public ItemResource getItem(String itemId) {
    return inventoriesQueryService.selectItem(new ItemId(itemId))
        .map(ItemResource::new)
        .orElseThrow(() -> new NotFoundException(""));
  }
}
