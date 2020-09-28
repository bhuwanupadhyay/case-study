package io.github.bhuwanupadhyay.casestudy.sales.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSource {
  
  @Output("orderPlaced")
  MessageChannel orderPlaced();

  @Output("orderModified")
  MessageChannel orderModified();

  @Output("orderCancelled")
  MessageChannel orderCancelled();
}
