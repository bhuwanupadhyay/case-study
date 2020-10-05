package io.github.bhuwanupadhyay.casestudy.fulfillment.interfaces;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.CancelShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.PrepareShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ShipOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.brokers.EventSource;
import java.util.Map;
import org.springframework.cloud.stream.annotation.StreamListener;

public interface EventHandler {

  @StreamListener(EventSource.ORDER_PLACED)
  void on(OrderPlacedPayload payload);

  @StreamListener(EventSource.ORDER_MODIFIED)
  void on(OrderModifiedPayload payload);

  @StreamListener(EventSource.ORDER_CANCELLED)
  void on(OrderCancelledPayload payload);

  @StreamListener(EventSource.ORDER_BILLED)
  void on(OrderBilledPayload payload);

  @StreamListener(EventSource.MODIFICATION_BILLED)
  void on(ModifiedBilledPayload payload);

  record OrderBilledPayload(String billingId,
                            String orderId,
                            String billAmount) {
    public ShipOrderCommand toCommand() {
      return new ShipOrderCommand(this.orderId);
    }
  }

  record ModifiedBilledPayload(String billingId,
                               String orderId,
                               String billAmount) {
    public ShipOrderCommand toCommand() {
      return new ShipOrderCommand(this.orderId);
    }
  }

  record OrderPlacedPayload(String orderId,
                            Map<String, Integer> orderItems) {
    public PrepareShippingCommand toCommand() {
      return new PrepareShippingCommand(this.orderId, this.orderItems);
    }
  }

  record OrderModifiedPayload(String orderId,
                              Map<String, Integer> orderItems) {
    public ModifyOrderCommand toCommand() {
      return new ModifyOrderCommand(this.orderId, this.orderItems);
    }
  }

  record OrderCancelledPayload(String orderId,
                               String cancellationReason) {
    public CancelShippingCommand toCommand() {
      return new CancelShippingCommand(this.orderId, this.cancellationReason);
    }
  }
}
