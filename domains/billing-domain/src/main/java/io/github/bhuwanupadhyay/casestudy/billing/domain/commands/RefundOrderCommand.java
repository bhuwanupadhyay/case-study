package io.github.bhuwanupadhyay.casestudy.billing.domain.commands;

public record RefundOrderCommand(String orderId, String reason) {
}
