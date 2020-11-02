package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

import java.util.List;

public record ChargeOrderCommand(String orderId, List<OrderItem> orderItems) {
}
