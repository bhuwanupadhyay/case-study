package io.github.bhuwanupadhyay.casestudy.notifications.domain.commands;

import java.util.Map;

public record OrderPlacedNotificationCommand(String orderId,
                                             Map<String, Integer> orderItems) {
}
