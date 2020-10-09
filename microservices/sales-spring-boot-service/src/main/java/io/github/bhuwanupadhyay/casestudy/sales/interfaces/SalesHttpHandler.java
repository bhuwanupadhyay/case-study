package io.github.bhuwanupadhyay.casestudy.sales.interfaces;

import io.github.bhuwanupadhyay.casestudy.sales.application.queryservices.OrdersQueryService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.CancelOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.ModifyOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.PlaceOrderCommandService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record SalesHttpHandler(
    PlaceOrderCommandService placeOrderCommandService,
    ModifyOrderCommandService modifyOrderCommandService,
    CancelOrderCommandService cancelOrderCommandService,
    OrdersQueryService ordersQueryService) implements HttpHandler {

  @Override public ResponseEntity<Void> placeOrder(PlaceOrderRequest request) {
    placeOrderCommandService.execute(request.toCommand());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override public ResponseEntity<Void> modifyOrder(String orderId, ModifyOrderRequest request) {
    modifyOrderCommandService.execute(request.toCommand(orderId));
    return ResponseEntity.ok().build();
  }

  @Override public ResponseEntity<Void> cancelOrder(String orderId, CancelOrderRequest request) {
    cancelOrderCommandService.execute(request.toCommand(orderId));
    return ResponseEntity.ok().build();
  }

  @Override public ResponseEntity<List<OrderResource>> getOrders() {
    return ResponseEntity.ok(ordersQueryService.findAll());
  }

  @Override public ResponseEntity<OrderResource> getOrder(String orderId) {
    return ResponseEntity.ok(ordersQueryService.findByOrderId(orderId));
  }
}
