package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

import java.util.Map;

public record ModifyChargeCommand(String billId,
                                  Map<String, Integer> orderItems) {
}
