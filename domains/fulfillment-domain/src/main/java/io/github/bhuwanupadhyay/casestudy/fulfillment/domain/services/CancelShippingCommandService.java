package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.CancelShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.PrepareShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingStatus;
import io.github.bhuwanupadhyay.core.CommandService;
import java.util.Objects;

public class CancelShippingCommandService implements CommandService<CancelShippingCommand> {

  private final Shippings shippings;

  public CancelShippingCommandService(Shippings shippings) {
    this.shippings = shippings;
  }

  @Override public void execute(CancelShippingCommand command) {
    Shipping shipping = shippings.findByOrderId(new OrderId(command.orderId()));
    if (shipping.isNotShipped()) {
      shipping.on(command);
      shippings.save(shipping);
    }
  }
}
