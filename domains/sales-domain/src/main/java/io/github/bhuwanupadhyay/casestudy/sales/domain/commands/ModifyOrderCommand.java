package io.github.bhuwanupadhyay.casestudy.sales.domain.commands;

import java.util.List;

public record ModifyOrderCommand(String orderId, List<OrderItem> orderItems) {
}
