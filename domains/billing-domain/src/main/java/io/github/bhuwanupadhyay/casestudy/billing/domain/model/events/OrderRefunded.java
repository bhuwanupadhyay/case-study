package io.github.bhuwanupadhyay.casestudy.billing.domain.model.events;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.RefundReason;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.ddd.DomainEvent;

public record OrderRefunded(BillingId billingId,
                            RefundReason refundReason) implements DomainEvent {

}
