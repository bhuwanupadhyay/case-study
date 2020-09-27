package io.github.bhuwanupadhyay.casestudy.sales.domain.commands;

public record CancelOrderCommand(String orderId,
                                 String reason) {
}
