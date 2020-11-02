package io.github.bhuwanupadhyay.casestudy.notifications.domain.model.repositories;

import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.aggregates.Notification;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.valueobjects.NotificationId;
import io.github.bhuwanupadhyay.ddd.AggregateRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;

public abstract class Notifications extends AggregateRepository<Notification, NotificationId> {

	protected Notifications(DomainEventPublisher publisher) {
		super(publisher);
	}

}
