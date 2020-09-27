package io.github.bhuwanupadhyay.casestudy.sales.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SalesHttpHandler {

  @PostMapping("/sales")
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

  record OrderResource() {
  }

  record CancelOrderRequest() {
  }

  record ModifyOrderRequest() {
  }

  record PlaceOrderRequest() {
  }
}
