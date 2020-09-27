package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.PrepareShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingAddresses;
import io.github.bhuwanupadhyay.core.CommandService;
import java.util.HashMap;

public class PrepareShippingOrderCommandService implements CommandService<PrepareShippingCommand> {

  private final Shippings shippings;

  public PrepareShippingOrderCommandService(Shippings shippings) {
    this.shippings = shippings;
  }

  @Override public void execute(PrepareShippingCommand command) {
    Shipping shipping =
        new Shipping(shippings.nextId(), command, new ShippingAddresses(new HashMap<>()));
    shippings.save(shipping);
  }
}
