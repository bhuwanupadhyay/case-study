package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

import java.util.List;

public record ModifyChargeCommand(String billId,
                                  List<OrderItem> orderItems) {
}
