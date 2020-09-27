package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

public record RefundOrderCommand(String billId,
                                 String reason) {
}
