package io.github.bhuwanupadhyay.casestudy.notifications.domain.services;

import io.github.bhuwanupadhyay.casestudy.notifications.domain.commands.OrderPlacedNotificationCommand;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.aggregates.Notification;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.repositories.Notifications;
import io.github.bhuwanupadhyay.core.CommandService;

public class OrderPlacedNotificationCommandService
    implements CommandService<OrderPlacedNotificationCommand> {

  private final Notifications notifications;

  public OrderPlacedNotificationCommandService(Notifications notifications) {
    this.notifications = notifications;
  }

  @Override public void execute(OrderPlacedNotificationCommand command) {
    Notification notification = new Notification(notifications.nextId(), command);
    notifications.save(notification);
  }
}
