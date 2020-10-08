package io.github.bhuwanupadhyay.casestudy.sales.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.CancelOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.PlaceOrderCommand;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface HttpHandler {

  static List<OrderItem> toOrderItems(List<OrderItemResource> orderItems) {
    return orderItems.stream()
        .map(e -> new OrderItem(e.itemId(), e.quantity()))
        .collect(Collectors.toList());
  }

  @PostMapping("/sales")
  ResponseEntity<Void> placeOrder(
      @RequestBody PlaceOrderRequest request
  );

  @PostMapping("/sales/{orderId}")
  ResponseEntity<Void> modifyOrder(
      @PathVariable("orderId") String orderId,
      @RequestBody ModifyOrderRequest request
  );

  @PostMapping("/sales/{orderId}/cancel")
  ResponseEntity<Void> cancelOrder(
      @PathVariable("orderId") String orderId,
      @RequestBody CancelOrderRequest request
  );

  @GetMapping("/sales")
  ResponseEntity<List<OrderResource>> getOrders();

  @GetMapping("/sales/{orderId}")
  ResponseEntity<OrderResource> getOrder(
      @PathVariable("orderId") String orderId
  );

  record OrderItemResource(@JsonProperty("itemId") String itemId,
                           @JsonProperty("quantity") Integer quantity) {
  }

  record OrderResource(@JsonProperty("orderId") String orderId,
                       @JsonProperty("orderItems") List<OrderItemResource> orderItems) {
  }

  record CancelOrderRequest(@JsonProperty("reason") String reason) {
    public CancelOrderCommand toCommand(String orderId) {
      return new CancelOrderCommand(orderId, reason);
    }
  }

  record ModifyOrderRequest(@JsonProperty("orderItems") List<OrderItemResource> orderItems) {
    public ModifyOrderCommand toCommand(String orderId) {
      return new ModifyOrderCommand(orderId, toOrderItems(orderItems));
    }
  }

  record PlaceOrderRequest(@JsonProperty("orderItems") List<OrderItemResource> orderItems,
                           @JsonProperty("customerId") String customerId) {
    public PlaceOrderCommand toCommand() {
      return new PlaceOrderCommand(toOrderItems(orderItems), customerId);
    }
  }
}
