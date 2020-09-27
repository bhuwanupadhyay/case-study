package io.github.bhuwanupadhyay.casestudy.sales.domain.services;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.PlaceOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.core.CommandService;

public class PlaceOrderCommandService implements CommandService<PlaceOrderCommand> {

  private final Orders billings;

  public PlaceOrderCommandService(Orders billings) {
    this.billings = billings;
  }

  @Override public void execute(PlaceOrderCommand command) {
    Order order = new Order(billings.nextId(), command);
    billings.save(order);
  }
}
