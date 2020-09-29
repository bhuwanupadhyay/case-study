package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface HttpHandler {

  @PostMapping("/inventories")
  ResponseEntity<String> placeOrder(
      @RequestBody PlaceOrderRequest request
  );

  @PatchMapping("/sales/{orderId}")
  ResponseEntity<String> modifyOrder(
      @PathVariable("orderId") String orderId,
      @RequestBody ModifyOrderRequest request
  );

  @PatchMapping("/sales/{orderId}/cancel")
  ResponseEntity<String> cancelOrder(
      @PathVariable("orderId") String orderId,
      @RequestBody CancelOrderRequest request
  );

  @GetMapping("/sales/{orderId}")
  ResponseEntity<OrderResource> getOrder(
      @PathVariable("orderId") String orderId
  );

  record OrderResource(String orderId,
                       Map<String, Integer> orderItems) {
  }

  record CancelOrderRequest(String reason) {
  }

  record ModifyOrderRequest(Map<String, Integer> orderItems) {
  }

  record PlaceOrderRequest(Map<String, Integer> orderItems,
                           String customerId) {
  }
}
