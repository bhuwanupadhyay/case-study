package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands;

import java.util.List;

public record PrepareShippingCommand(String orderId,
                                     List<OrderItem> orderItems) {
}
