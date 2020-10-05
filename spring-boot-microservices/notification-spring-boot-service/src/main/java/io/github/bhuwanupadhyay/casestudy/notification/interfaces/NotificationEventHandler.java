package io.github.bhuwanupadhyay.casestudy.notification.interfaces;

import org.springframework.stereotype.Service;

@Service
public class NotificationEventHandler implements EventHandler {

  @Override public void on(OrderPlacedPayload payload) {

  }

  @Override public void on(OrderShippedPayload payload) {

  }

  @Override public void on(OrderRefundedPayload payload) {

  }
}
