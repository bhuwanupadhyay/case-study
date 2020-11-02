package io.github.bhuwanupadhyay.casestudy.fulfillment.application.outboundservices;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.events.OrderShipped;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingAddress;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.brokers.EventSource;
import io.github.bhuwanupadhyay.ddd.DomainEvent;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DomainEventPublisherService implements DomainEventPublisher {

	private final EventSource eventSource;

	public DomainEventPublisherService(EventSource eventSource) {
		this.eventSource = eventSource;
	}

	public void publish(DomainEvent domainEvent) {
		Map<String, Object> h = new HashMap<>();
		h.put("X-Service", "Fulfillment");
		h.put("X-DomainEvent", domainEvent.getClass().getName());
		MessageHeaders headers = new MessageHeaders(h);

		if (domainEvent instanceof OrderShipped orderShipped) {
			Map<String, Object> payload = toPayload(orderShipped.shippingId(), orderShipped.orderId(),
					orderShipped.shippingAddress());
			eventSource.orderShipped().send(MessageBuilder.createMessage(payload, headers));
		}
	}

	private Map<String, Object> toPayload(ShippingId shippingId, OrderId orderId, ShippingAddress shippingAddress) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("shippingId", shippingId.value());
		payload.put("orderId", orderId.value());
		payload.put("shippingAddress", shippingAddress.value());
		return payload;
	}

}
