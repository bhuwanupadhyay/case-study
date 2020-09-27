package io.github.bhuwanupadhyay.casestudy.sales.domain.services;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.CancelOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.core.CommandService;

public class CancelOrderCommandService implements CommandService<CancelOrderCommand> {

  private final Orders orders;

  public CancelOrderCommandService(Orders orders) {
    this.orders = orders;
  }

  @Override public void execute(CancelOrderCommand command) {
    Order order = orders.find(new OrderId(command.orderId()));
    order.on(command);
    orders.save(order);
  }
}
