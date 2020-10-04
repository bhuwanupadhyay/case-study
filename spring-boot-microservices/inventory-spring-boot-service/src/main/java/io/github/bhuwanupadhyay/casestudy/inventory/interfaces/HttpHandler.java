package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface HttpHandler {

  @GetMapping("/inventories/{inventoryId}/items")
  List<ItemResource> getItems(@PathVariable("inventoryId") String inventoryId);
}
