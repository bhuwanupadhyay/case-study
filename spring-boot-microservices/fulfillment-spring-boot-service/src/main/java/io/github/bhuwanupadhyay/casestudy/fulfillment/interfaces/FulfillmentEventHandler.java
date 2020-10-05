package io.github.bhuwanupadhyay.casestudy.fulfillment.interfaces;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.CancelShippingCommandService;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.ModifyShippingCommandService;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.PrepareShippingCommandService;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.ShipOrderCommandService;
import org.springframework.stereotype.Service;

@Service
public class FulfillmentEventHandler implements EventHandler {

  private final PrepareShippingCommandService prepareShippingCommandService;
  private final ModifyShippingCommandService modifyShippingCommandService;
  private final ShipOrderCommandService shipOrderCommandService;
  private final CancelShippingCommandService cancelShippingCommandService;

  public FulfillmentEventHandler(
      PrepareShippingCommandService prepareShippingCommandService,
      ModifyShippingCommandService modifyShippingCommandService,
      ShipOrderCommandService shipOrderCommandService,
      CancelShippingCommandService cancelShippingCommandService) {
    this.prepareShippingCommandService = prepareShippingCommandService;
    this.modifyShippingCommandService = modifyShippingCommandService;
    this.shipOrderCommandService = shipOrderCommandService;
    this.cancelShippingCommandService = cancelShippingCommandService;
  }

  @Override public void on(OrderPlacedPayload payload) {
    this.prepareShippingCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderModifiedPayload payload) {
    this.modifyShippingCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderCancelledPayload payload) {
    this.cancelShippingCommandService.execute(payload.toCommand());
  }

  @Override public void on(OrderBilledPayload payload) {
    this.shipOrderCommandService.execute(payload.toCommand());
  }

  @Override public void on(ModifiedBilledPayload payload) {
    this.shipOrderCommandService.execute(payload.toCommand());
  }
}
