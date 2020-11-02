package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventSource {

	String ORDER_PLACED = "orderPlaced";

	String ORDER_MODIFIED = "orderModified";

	String ORDER_CANCELLED = "orderCancelled";

	@Input(ORDER_PLACED)
	SubscribableChannel orderPlaced();

	@Input(ORDER_MODIFIED)
	SubscribableChannel orderModified();

	@Input(ORDER_CANCELLED)
	SubscribableChannel orderCancelled();

	@Output("orderBilled")
	MessageChannel orderBilled();

	@Output("modificationBilled")
	MessageChannel modificationBilled();

	@Output("orderRefunded")
	MessageChannel orderRefunded();

}
