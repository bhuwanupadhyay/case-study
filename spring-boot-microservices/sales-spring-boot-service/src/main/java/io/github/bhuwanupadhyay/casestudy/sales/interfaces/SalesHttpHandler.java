package io.github.bhuwanupadhyay.casestudy.sales.interfaces;

import io.github.bhuwanupadhyay.casestudy.sales.application.queryservices.OrdersQueryService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.CancelOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.ModifyOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.PlaceOrderCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesHttpHandler implements HttpHandler {

  private final PlaceOrderCommandService placeOrderCommandService;
  private final ModifyOrderCommandService modifyOrderCommandService;
  private final CancelOrderCommandService cancelOrderCommandService;
  private final OrdersQueryService ordersQueryService;

  public SalesHttpHandler(
      PlaceOrderCommandService placeOrderCommandService,
      ModifyOrderCommandService modifyOrderCommandService,
      CancelOrderCommandService cancelOrderCommandService,
      OrdersQueryService ordersQueryService) {
    this.placeOrderCommandService = placeOrderCommandService;
    this.modifyOrderCommandService = modifyOrderCommandService;
    this.cancelOrderCommandService = cancelOrderCommandService;
    this.ordersQueryService = ordersQueryService;
  }

  @Override public ResponseEntity<String> placeOrder(PlaceOrderRequest request) {
    placeOrderCommandService.execute(request.toCommand());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override public ResponseEntity<String> modifyOrder(String orderId, ModifyOrderRequest request) {
    modifyOrderCommandService.execute(request.toCommand(orderId));
    return ResponseEntity.ok().build();
  }

  @Override public ResponseEntity<String> cancelOrder(String orderId, CancelOrderRequest request) {
    cancelOrderCommandService.execute(request.toCommand(orderId));
    return ResponseEntity.ok().build();
  }

  @Override public ResponseEntity<OrderResource> getOrder(String orderId) {
    return ResponseEntity.ok(ordersQueryService.findByOrderId(orderId));
  }
}
