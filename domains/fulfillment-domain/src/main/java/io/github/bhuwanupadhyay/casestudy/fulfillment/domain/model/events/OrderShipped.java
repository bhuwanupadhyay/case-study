package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.events;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingAddress;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.ddd.DomainEvent;

public record OrderShipped(ShippingId shippingId,
                           OrderId orderId,
                           ShippingAddress shippingAddress) implements DomainEvent {

}
