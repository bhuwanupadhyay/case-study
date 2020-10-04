package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import io.github.bhuwanupadhyay.casestudy.inventory.application.queryservices.InventoriesQueryService;
import io.github.bhuwanupadhyay.casestudy.inventory.domain.InventoryId;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryHttpHandler implements HttpHandler {

  private final InventoriesQueryService inventoriesQueryService;

  public InventoryHttpHandler(InventoriesQueryService inventoriesQueryService) {
    this.inventoriesQueryService = inventoriesQueryService;
  }

  @Override
  public List<ItemResource> getItems(String inventoryId) {
    return inventoriesQueryService.selectItems(new InventoryId(inventoryId));
  }
}
