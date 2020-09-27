package io.github.bhuwanupadhyay.casestudy.sales.application.outboundservices;

import io.github.bhuwanupadhyay.casestudy.sales.infrastructure.brokers.EventSource;
import io.github.bhuwanupadhyay.ddd.DomainEvent;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DomainEventPublisherService implements DomainEventPublisher {

  private final EventSource eventSource;

  public DomainEventPublisherService(EventSource eventSource) {
    this.eventSource = eventSource;
  }

  @EventListener
  public void publish(DomainEvent domainEvent) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("X-Service", "Product");
    eventSource.eventsOut()
        .send(MessageBuilder.createMessage(domainEvent, new MessageHeaders(headers)));
  }
}
