package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ChargeOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import io.github.bhuwanupadhyay.core.CommandService;
import java.util.HashMap;

public class ChargeOrderCommandService implements CommandService<ChargeOrderCommand> {

  private final Billings billings;

  public ChargeOrderCommandService(Billings billings) {
    this.billings = billings;
  }

  @Override public void execute(ChargeOrderCommand command) {
    Billing billing = new Billing(billings.nextId(), command, new Rates(new HashMap<>()));
    billings.save(billing);
  }
}
