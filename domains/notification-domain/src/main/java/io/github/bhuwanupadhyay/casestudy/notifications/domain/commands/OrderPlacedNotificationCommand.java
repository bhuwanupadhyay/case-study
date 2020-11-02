package io.github.bhuwanupadhyay.casestudy.notifications.domain.commands;

import java.util.List;

public record OrderPlacedNotificationCommand(String orderId, List<OrderItem> orderItems) {
}
