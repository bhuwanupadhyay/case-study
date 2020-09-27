package io.github.bhuwanupadhyay.casestudy.sales.domain.services;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.core.CommandService;

public class ModifyOrderCommandService implements CommandService<ModifyOrderCommand> {

  private final Orders billings;

  public ModifyOrderCommandService(Orders billings) {
    this.billings = billings;
  }

  @Override public void execute(ModifyOrderCommand command) {
    Order order = billings.find(new OrderId(command.orderId()));
    order.on(command);
    billings.save(order);
  }
}
