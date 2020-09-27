package io.github.bhuwanupadhyay.casestudy.sales.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSalesHttpHandler implements SalesHttpHandler {
  @Override public ResponseEntity<String> placeOrder(PlaceOrderRequest request) {
    return null;
  }

  @Override public ResponseEntity<String> modifyOrder(String orderId, ModifyOrderRequest request) {
    return null;
  }

  @Override public ResponseEntity<String> cancelOrder(String orderId, CancelOrderRequest request) {
    return null;
  }

  @Override public ResponseEntity<OrderResource> getOrder(String orderId) {
    return null;
  }
}
