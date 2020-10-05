package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands;

import java.util.Map;

public record ModifyShippingCommand(String shippingId,
                                    String orderId,
                                    Map<String, Integer> orderItems) {
}
