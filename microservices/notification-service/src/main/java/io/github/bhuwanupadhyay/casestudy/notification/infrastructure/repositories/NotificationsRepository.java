package io.github.bhuwanupadhyay.casestudy.notification.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.aggregates.Notification;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.repositories.Notifications;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.valueobjects.NotificationId;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NotificationsRepository extends Notifications {

	protected NotificationsRepository(DomainEventPublisher publisher) {
		super(publisher);
	}

	@Override
	public Optional<Notification> findOne(NotificationId notificationId) {
		return Optional.empty();
	}

	@Override
	protected void persist(Notification entity) {

	}

	@Override
	public NotificationId nextId() {
		return null;
	}

}
