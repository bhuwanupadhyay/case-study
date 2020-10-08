package io.github.bhuwanupadhyay.casestudy.billing.interfaces;

import io.github.bhuwanupadhyay.casestudy.billing.domain.services.ChargeOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.ModifyChargeCommandService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.RefundOrderCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BillingEventHandler implements EventHandler {

  private final ChargeOrderCommandService chargeOrderCommandService;
  private final ModifyChargeCommandService modifyChargeCommandService;
  private final RefundOrderCommandService refundOrderCommandService;
  private final static Logger LOG = LoggerFactory.getLogger(BillingEventHandler.class);

  public BillingEventHandler(
      ChargeOrderCommandService chargeOrderCommandService,
      ModifyChargeCommandService modifyChargeCommandService,
      RefundOrderCommandService refundOrderCommandService) {
    this.chargeOrderCommandService = chargeOrderCommandService;
    this.modifyChargeCommandService = modifyChargeCommandService;
    this.refundOrderCommandService = refundOrderCommandService;
  }

  @Override public void on(OrderPlacedPayload payload) {
    LOG.info("Received: {}", payload);
    chargeOrderCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderModifiedPayload payload) {
    LOG.info("Received: {}", payload);
    modifyChargeCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderCancelledPayload payload) {
    LOG.info("Received: {}", payload);
    refundOrderCommandService.execute(payload.toCommand());
  }
}
