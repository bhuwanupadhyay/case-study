package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.RefundOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.core.CommandService;

public class RefundOrderCommandService implements CommandService<RefundOrderCommand> {

  private final Billings billings;

  public RefundOrderCommandService(Billings billings) {
    this.billings = billings;
  }

  @Override public void execute(RefundOrderCommand command) {
    Billing billing = billings.findByOrderId(new OrderId(command.orderId()));
    billing.on(command);
    billings.save(billing);
  }
}
