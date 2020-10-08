package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface HttpHandler {

  @GetMapping("/items")
  List<ItemResource> getItems();

  @GetMapping("/items/{itemId}")
  ItemResource getItem(@PathVariable("itemId") String itemId);
}
