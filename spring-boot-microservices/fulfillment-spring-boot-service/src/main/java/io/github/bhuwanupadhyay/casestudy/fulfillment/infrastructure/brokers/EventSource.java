package io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventSource {

  String ORDER_PLACED = "orderPlaced";
  String ORDER_MODIFIED = "orderModified";
  String ORDER_CANCELLED = "orderCancelled";
  String MODIFICATION_BILLED = "modificationBilled";
  String ORDER_BILLED = "orderBilled";

  @Input(ORDER_PLACED)
  SubscribableChannel orderPlaced();

  @Input(ORDER_MODIFIED)
  SubscribableChannel orderModified();

  @Input(ORDER_CANCELLED)
  SubscribableChannel orderCancelled();

  @Input(ORDER_BILLED)
  SubscribableChannel orderBilled();

  @Input(MODIFICATION_BILLED)
  SubscribableChannel modificationBilled();

  @Output("orderShipped")
  MessageChannel orderShipped();
}
