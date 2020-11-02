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
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public record DomainEventPublisherService(EventSource eventSource) implements DomainEventPublisher {

	public void publish(DomainEvent domainEvent) {
		Map<String, Object> h = new HashMap<>();
		h.put("X-Service", "Sales");
		h.put("X-DomainEvent", domainEvent.getClass().getName());
		MessageHeaders headers = new MessageHeaders(h);

		if (domainEvent instanceof OrderPlaced orderPlaced) {
			Map<String, Object> payload = toPayload(orderPlaced.orderId(), orderPlaced.orderItems());
			eventSource.orderPlaced().send(MessageBuilder.createMessage(payload, headers));
		}
		else if (domainEvent instanceof OrderModified orderModified) {
			Map<String, Object> payload = toPayload(orderModified.orderId(), orderModified.orderItems());
			eventSource.orderModified().send(MessageBuilder.createMessage(payload, headers));
		}
		else if (domainEvent instanceof OrderCancelled orderCancelled) {
			Map<String, Object> payload = toPayload(orderCancelled.orderId());
			payload.put("cancellationReason", orderCancelled.cancellationReason().value());
			eventSource.orderCancelled().send(MessageBuilder.createMessage(payload, headers));
		}
	}

	private Map<String, Object> toPayload(OrderId orderId, Map<ItemId, Quantity> orderItems) {
		Map<String, Object> payload = toPayload(orderId);
		List<Map<String, Object>> items = new ArrayList<>();
		for (Map.Entry<ItemId, Quantity> e : orderItems.entrySet()) {
			Map<String, Object> item = new HashMap<>();
			item.put("itemId", e.getKey().value());
			item.put("quantity", e.getValue().value());
			items.add(item);
		}
		payload.put("orderItems", items);
		return payload;
	}

	private Map<String, Object> toPayload(OrderId orderId) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("orderId", orderId.value());
		return payload;
	}
}
