package io.github.bhuwanupadhyay.casestudy.notification.interfaces;

import io.github.bhuwanupadhyay.casestudy.notification.infrastructure.brokers.EventSource;
import java.util.Map;
import org.springframework.cloud.stream.annotation.StreamListener;

public interface EventHandler {

  @StreamListener(EventSource.ORDER_PLACED)
  void on(OrderPlacedPayload payload);

  @StreamListener(EventSource.ORDER_SHIPPED)
  void on(OrderShippedPayload payload);

  @StreamListener(EventSource.ORDER_REFUNDED)
  void on(OrderRefundedPayload payload);

  record OrderPlacedPayload(String orderId,
                            Map<String, Integer> orderItems) {
  }

  record OrderShippedPayload(String orderId,
                             Map<String, Integer> orderItems) {
  }

  record OrderRefundedPayload(String orderId,
                              String cancellationReason) {
  }
}
