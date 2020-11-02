package io.github.bhuwanupadhyay.casestudy.billing.application.outboundservices;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.ModificationBilled;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.OrderBilled;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.OrderRefunded;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillAmount;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.RefundReason;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.brokers.EventSource;
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
		h.put("X-Service", "Billing");
		h.put("X-DomainEvent", domainEvent.getClass().getName());
		MessageHeaders headers = new MessageHeaders(h);

		if (domainEvent instanceof OrderBilled orderBilled) {
			Map<String, Object> payload = toPayload(orderBilled.billingId(), orderBilled.orderId(),
					orderBilled.billAmount());
			eventSource.orderBilled().send(MessageBuilder.createMessage(payload, headers));
		}
		else if (domainEvent instanceof ModificationBilled modificationBilled) {
			Map<String, Object> payload = toPayload(modificationBilled.billingId(), modificationBilled.orderId(),
					modificationBilled.billAmount());
			eventSource.modificationBilled().send(MessageBuilder.createMessage(payload, headers));
		}
		else if (domainEvent instanceof OrderRefunded orderRefunded) {
			Map<String, Object> payload = toPayload(orderRefunded.billingId(), orderRefunded.refundReason());
			eventSource.orderRefunded().send(MessageBuilder.createMessage(payload, headers));
		}
	}

	private Map<String, Object> toPayload(BillingId billingId, OrderId orderId, BillAmount billAmount) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("billingId", billingId.value());
		payload.put("orderId", orderId.value());
		payload.put("billAmount", billAmount.amount());
		return payload;
	}

	private Map<String, Object> toPayload(BillingId billingId, RefundReason refundReason) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("billingId", billingId.value());
		payload.put("refundReason", refundReason.value());
		return payload;
	}

}
