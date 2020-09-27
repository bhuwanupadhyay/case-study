package io.github.bhuwanupadhyay.casestudy.sales.domain.services;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.core.CommandService;

public class ModifyOrderCommandService implements CommandService<ModifyOrderCommand> {

  private final Orders orders;

  public ModifyOrderCommandService(Orders orders) {
    this.orders = orders;
  }

  @Override public void execute(ModifyOrderCommand command) {
    Order order = orders.find(new OrderId(command.orderId()));
    order.on(command);
    orders.save(order);
  }
}
