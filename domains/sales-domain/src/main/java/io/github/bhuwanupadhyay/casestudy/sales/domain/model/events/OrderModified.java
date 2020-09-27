package io.github.bhuwanupadhyay.casestudy.sales.domain.model.events;

import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.Quantity;
import io.github.bhuwanupadhyay.ddd.DomainEvent;
import java.util.Map;

public record OrderModified(OrderId orderId,
                            Map<ItemId, Quantity> orderItems) implements DomainEvent {

}
