package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.ddd.AggregateRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;

import java.util.Optional;

public abstract class Shippings extends AggregateRepository<Shipping, ShippingId> {

	protected Shippings(DomainEventPublisher publisher) {
		super(publisher);
	}

	public abstract Optional<Shipping> findByOrderIdAndStatusNotShipped(OrderId orderId);

}
