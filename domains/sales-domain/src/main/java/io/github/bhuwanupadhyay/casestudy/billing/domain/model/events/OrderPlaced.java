package io.github.bhuwanupadhyay.casestudy.billing.domain.model.events;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Quantity;
import io.github.bhuwanupadhyay.ddd.DomainEvent;
import java.util.Map;

public record OrderPlaced(OrderId orderId,
                          Map<ItemId, Quantity> orderItems) implements DomainEvent {

}
