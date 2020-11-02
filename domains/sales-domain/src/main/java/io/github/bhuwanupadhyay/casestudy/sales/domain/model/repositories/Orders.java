package io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories;

import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.ddd.AggregateRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;

public abstract class Orders extends AggregateRepository<Order, OrderId> {

	protected Orders(DomainEventPublisher publisher) {
		super(publisher);
	}

}
