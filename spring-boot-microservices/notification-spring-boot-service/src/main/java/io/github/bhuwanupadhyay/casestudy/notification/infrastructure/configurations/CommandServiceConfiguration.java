package io.github.bhuwanupadhyay.casestudy.notification.infrastructure.configurations;

import io.github.bhuwanupadhyay.casestudy.notifications.domain.model.repositories.Notifications;
import io.github.bhuwanupadhyay.casestudy.notifications.domain.services.OrderPlacedNotificationCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandServiceConfiguration {

  @Bean
  public OrderPlacedNotificationCommandService orderPlacedNotificationCommand(Notifications notifications) {
    return new OrderPlacedNotificationCommandService(notifications);
  }

}
