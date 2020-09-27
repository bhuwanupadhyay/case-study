package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ModifyChargeCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import io.github.bhuwanupadhyay.core.CommandService;
import java.util.HashMap;

public class ModifyChargeCommandService implements CommandService<ModifyChargeCommand> {

  private final Billings billings;

  public ModifyChargeCommandService(Billings billings) {
    this.billings = billings;
  }

  @Override public void execute(ModifyChargeCommand command) {
    Billing billing = billings.find(new BillingId(command.billId()));
    billing.on(command, new Rates(new HashMap<>()));
    billings.save(billing);
  }
}
