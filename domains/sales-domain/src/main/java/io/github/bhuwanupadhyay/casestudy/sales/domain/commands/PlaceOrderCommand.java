package io.github.bhuwanupadhyay.casestudy.sales.domain.commands;

import java.util.Map;

public record PlaceOrderCommand(Map<String, Integer> orderItems,
                                String customerId) {
}
