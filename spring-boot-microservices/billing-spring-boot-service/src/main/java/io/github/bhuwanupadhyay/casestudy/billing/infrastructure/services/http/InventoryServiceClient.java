package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.services.http;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "InventoryService", url = "${InventoryService.url}")
public interface InventoryServiceClient {

  ItemInfoResouce getItemInfo(String itemId);

  record ItemInfoResouce(String itemId, BigDecimal price) {
  }
}
