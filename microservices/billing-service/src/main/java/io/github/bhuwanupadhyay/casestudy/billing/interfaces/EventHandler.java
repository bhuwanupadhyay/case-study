package io.github.bhuwanupadhyay.casestudy.billing.interfaces;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ChargeOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ModifyChargeCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.RefundOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.brokers.EventSource;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.List;

public interface EventHandler {

	@StreamListener(EventSource.ORDER_PLACED)
	void on(OrderPlacedPayload payload);

	@StreamListener(EventSource.ORDER_MODIFIED)
	void on(OrderModifiedPayload payload);

	@StreamListener(EventSource.ORDER_CANCELLED)
	void on(OrderCancelledPayload payload);

	record OrderPlacedPayload(String orderId, List<OrderItem> orderItems) {
		public ChargeOrderCommand toCommand() {
			return new ChargeOrderCommand(this.orderId(), this.orderItems());
		}
	}

	record OrderModifiedPayload(String orderId, List<OrderItem> orderItems) {
		public ModifyChargeCommand toCommand() {
			return new ModifyChargeCommand(this.orderId(), this.orderItems());
		}
	}

	record OrderCancelledPayload(String orderId, String cancellationReason) {
		public RefundOrderCommand toCommand() {
			return new RefundOrderCommand(this.orderId(), this.cancellationReason());
		}
	}

}
