package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ShipOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.core.CommandService;

public class ShipOrderCommandService implements CommandService<ShipOrderCommand> {

  private final Shippings shippings;

  public ShipOrderCommandService(Shippings shippings) {
    this.shippings = shippings;
  }

  @Override public void execute(ShipOrderCommand command) {
    Shipping shipping = shippings.find(new ShippingId(command.shippingId()));
    shipping.on(command);
    shippings.save(shipping);
  }
}
