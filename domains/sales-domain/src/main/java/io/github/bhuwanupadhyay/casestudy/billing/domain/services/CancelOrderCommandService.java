package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.CancelOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.core.CommandService;

public class CancelOrderCommandService implements CommandService<CancelOrderCommand> {

  private final Orders billings;

  public CancelOrderCommandService(Orders billings) {
    this.billings = billings;
  }

  @Override public void execute(CancelOrderCommand command) {
    Order order = billings.find(new OrderId(command.orderId()));
    order.on(command);
    billings.save(order);
  }
}
