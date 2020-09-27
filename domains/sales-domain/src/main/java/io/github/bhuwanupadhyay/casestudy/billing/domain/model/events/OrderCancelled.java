package io.github.bhuwanupadhyay.casestudy.billing.domain.model.events;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.CancellationReason;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.ddd.DomainEvent;

public record OrderCancelled(OrderId orderId,
                             CancellationReason cancellationReason) implements DomainEvent {

}
