package io.github.bhuwanupadhyay.casestudy.notification.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationEventHandler implements EventHandler {

	private final static Logger LOG = LoggerFactory.getLogger(NotificationEventHandler.class);

	@Override
	public void on(OrderPlacedPayload payload) {
		LOG.info("Received: {}", payload);
	}

	@Override
	public void on(OrderShippedPayload payload) {
		LOG.info("Received: {}", payload);
	}

	@Override
	public void on(OrderRefundedPayload payload) {
		LOG.info("Received: {}", payload);
	}

}
