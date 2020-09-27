package io.github.bhuwanupadhyay.casestudy.sales.domain.commands;

import java.util.Map;

public record ModifyOrderCommand(String orderId,
                                 Map<String, Integer> orderItems) {
}
