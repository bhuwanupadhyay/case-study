package io.github.bhuwanupadhyay.casestudy.sales.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventSource {

  String CHANNEL = "osEvents";

  @Output(CHANNEL)
  MessageChannel eventsOut();

  @Output("orderPlaced")
  MessageChannel orderPlaced();

  @Output("orderModified")
  MessageChannel orderModified();

  @Output("orderCancelled")
  MessageChannel orderCancelled();

  @Input(CHANNEL)
  SubscribableChannel eventsIn();
}
