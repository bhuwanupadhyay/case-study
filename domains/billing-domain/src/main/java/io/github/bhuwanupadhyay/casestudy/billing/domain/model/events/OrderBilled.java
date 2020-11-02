package io.github.bhuwanupadhyay.casestudy.billing.domain.model.events;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillAmount;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.ddd.DomainEvent;

public record OrderBilled(BillingId billingId, OrderId orderId, BillAmount billAmount) implements DomainEvent {

}
