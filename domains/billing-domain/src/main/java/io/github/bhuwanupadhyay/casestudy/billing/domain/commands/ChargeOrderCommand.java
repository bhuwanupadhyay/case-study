package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

import java.util.Map;

public record ChargeOrderCommand(String orderId,
                                 Map<String, Integer> orderItems) {
}
