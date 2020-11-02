package io.github.bhuwanupadhyay.casestudy.notifications.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.notifications.domain.commands.OrderPlacedNotificationCommand;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.valueobjects.CustomerId;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.valueobjects.NotificationId;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;

public class Notification extends AggregateRoot<NotificationId> {

	private final OrderId orderId;

	private final CustomerId customerId;

	private ShippingId shippingId;

	public Notification(NotificationId notificationId, OrderPlacedNotificationCommand command) {
		super(notificationId);
		this.orderId = new OrderId(command.orderId());
		this.customerId = new CustomerId(command.orderId());
	}

	public OrderId getOrderId() {
		return orderId;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public ShippingId getShippingId() {
		return shippingId;
	}

}
