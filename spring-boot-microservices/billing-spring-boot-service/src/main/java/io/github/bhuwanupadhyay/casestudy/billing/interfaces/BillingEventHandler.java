package io.github.bhuwanupadhyay.casestudy.billing.interfaces;

import io.github.bhuwanupadhyay.casestudy.billing.domain.services.ChargeOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.ModifyChargeCommandService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.RefundOrderCommandService;
import org.springframework.stereotype.Service;

@Service
public class BillingEventHandler implements EventHandler {

  private final ChargeOrderCommandService chargeOrderCommandService;
  private final ModifyChargeCommandService modifyChargeCommandService;
  private final RefundOrderCommandService refundOrderCommandService;

  public BillingEventHandler(
      ChargeOrderCommandService chargeOrderCommandService,
      ModifyChargeCommandService modifyChargeCommandService,
      RefundOrderCommandService refundOrderCommandService) {
    this.chargeOrderCommandService = chargeOrderCommandService;
    this.modifyChargeCommandService = modifyChargeCommandService;
    this.refundOrderCommandService = refundOrderCommandService;
  }

  @Override public void on(OrderPlacedPayload payload) {
    chargeOrderCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderModifiedPayload payload) {
    modifyChargeCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderCancelledPayload payload) {
    refundOrderCommandService.execute(payload.toCommand());
  }
}
