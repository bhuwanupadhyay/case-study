package io.github.bhuwanupadhyay.casestudy.sales.interfaces;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.CancelOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.PlaceOrderCommand;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface HttpHandler {

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

  record OrderResource(String orderId,
                       List<OrderItem> orderItems) {
  }

  record CancelOrderRequest(String reason) {
    public CancelOrderCommand toCommand(String orderId) {
      return new CancelOrderCommand(orderId, reason);
    }
  }

  record ModifyOrderRequest(List<OrderItem> orderItems) {
    public ModifyOrderCommand toCommand(String orderId) {
      return new ModifyOrderCommand(orderId, orderItems);
    }
  }

  record PlaceOrderRequest(List<OrderItem> orderItems,
                           String customerId) {
    public PlaceOrderCommand toCommand() {
      return new PlaceOrderCommand(orderItems, customerId);
    }
  }
}
