package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands;

public record CancelShippingCommand(String orderId,
                                    String cancellationReason) {
}
