package io.github.bhuwanupadhyay.casestudy.sales.application.outboundservices;

import io.github.bhuwanupadhyay.casestudy.sales.domain.model.events.OrderCancelled;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.events.OrderModified;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.events.OrderPlaced;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.Quantity;
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
    Map<String, Object> h = new HashMap<>();
    h.put("X-Service", "Sales");
    h.put("X-DomainEvent", domainEvent.getClass().getName());
    MessageHeaders headers = new MessageHeaders(h);

    if (domainEvent instanceof OrderPlaced orderPlaced) {
      Map<String, Object> payload = toPayload(orderPlaced.orderId(), orderPlaced.orderItems());
      eventSource.orderPlaced().send(MessageBuilder.createMessage(payload, headers));
    } else if (domainEvent instanceof OrderModified orderModified) {
      Map<String, Object> payload = toPayload(orderModified.orderId(), orderModified.orderItems());
      eventSource.orderModified().send(MessageBuilder.createMessage(payload, headers));
    } else if (domainEvent instanceof OrderCancelled orderCancelled) {
      Map<String, Object> payload = toPayload(orderCancelled.orderId());
      payload.put("cancellationReason", orderCancelled.cancellationReason());
      eventSource.orderCancelled().send(MessageBuilder.createMessage(domainEvent, headers));
    }
  }

  private Map<String, Object> toPayload(OrderId orderId, Map<ItemId, Quantity> orderItems) {
    Map<String, Object> payload = toPayload(orderId);
    Map<Object, Object> items = new HashMap<>();
    for (Map.Entry<ItemId, Quantity> e : orderItems.entrySet()) {
      items.put("itemId", e.getKey().value());
      items.put("quantity", e.getValue().value());
    }
    payload.put("orderItems", items);
    return payload;
  }

  private Map<String, Object> toPayload(OrderId orderId) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("orderId", orderId);
    return payload;
  }
}
