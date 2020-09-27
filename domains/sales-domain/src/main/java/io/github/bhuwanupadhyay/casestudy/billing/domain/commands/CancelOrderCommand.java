package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

public record CancelOrderCommand(String orderId,
                                 String reason) {
}
