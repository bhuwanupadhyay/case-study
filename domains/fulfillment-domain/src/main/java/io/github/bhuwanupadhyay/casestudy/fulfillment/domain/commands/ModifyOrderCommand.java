package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands;

import java.util.List;

public record ModifyOrderCommand(String orderId,
                                 List<OrderItem> orderItems) {
}
