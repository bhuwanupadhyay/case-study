package io.github.bhuwanupadhyay.casestudy.sales.domain.commands;

import java.util.List;

public record PlaceOrderCommand(List<OrderItem> orderItems,
                                String customerId) {
}
