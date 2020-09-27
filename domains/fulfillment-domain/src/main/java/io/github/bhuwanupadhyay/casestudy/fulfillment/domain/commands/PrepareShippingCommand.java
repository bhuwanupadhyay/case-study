package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands;

import java.util.Map;

public record PrepareShippingCommand(String orderId,
                                     Map<String, Integer> orderItems) {
}
