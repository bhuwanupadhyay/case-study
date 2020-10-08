package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ModifyChargeCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import io.github.bhuwanupadhyay.core.CommandService;

public class ModifyChargeCommandService implements CommandService<ModifyChargeCommand> {

  private final Billings billings;
  private final InventoryService inventoryService;

  public ModifyChargeCommandService(Billings billings, InventoryService inventoryService) {
    this.billings = billings;
    this.inventoryService = inventoryService;
  }

  @Override public void execute(ModifyChargeCommand command) {
    Rates rates = ServiceUtils.getRates(inventoryService, command.orderItems());
    Billing billing = billings.find(new BillingId(command.billId()));
    billing.on(command, rates);
    billings.save(billing);
  }
}
