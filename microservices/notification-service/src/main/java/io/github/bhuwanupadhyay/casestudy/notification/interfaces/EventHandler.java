package io.github.bhuwanupadhyay.casestudy.notification.interfaces;

import io.github.bhuwanupadhyay.casestudy.notification.infrastructure.brokers.EventSource;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.commands.OrderItem;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.List;

public interface EventHandler {

	@StreamListener(EventSource.ORDER_PLACED)
	void on(OrderPlacedPayload payload);

	@StreamListener(EventSource.ORDER_SHIPPED)
	void on(OrderShippedPayload payload);

	@StreamListener(EventSource.ORDER_REFUNDED)
	void on(OrderRefundedPayload payload);

	record OrderPlacedPayload(String orderId, List<OrderItem> orderItems) {
	}

	record OrderShippedPayload(String shippingId, String orderId, String shippingAddress) {
	}

	record OrderRefundedPayload(String billingId, String refundReason) {
	}

}
