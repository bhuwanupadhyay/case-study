package io.github.bhuwanupadhyay.casestudy.notification.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventSource {

	String ORDER_PLACED = "orderPlaced";

	String ORDER_SHIPPED = "orderShipped";

	String ORDER_REFUNDED = "orderRefunded";

	@Input(ORDER_PLACED)
	SubscribableChannel orderPlaced();

	@Input(ORDER_SHIPPED)
	SubscribableChannel orderShipped();

	@Input(ORDER_REFUNDED)
	SubscribableChannel orderRefunded();

}
