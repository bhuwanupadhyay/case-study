package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ChargeOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Price;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import io.github.bhuwanupadhyay.core.CommandService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ChargeOrderCommandService implements CommandService<ChargeOrderCommand> {

  private final Billings billings;
  private final InventoryService inventoryService;

  public ChargeOrderCommandService(Billings billings, InventoryService inventoryService) {
    this.billings = billings;
    this.inventoryService = inventoryService;
  }

  @Override
  public void execute(ChargeOrderCommand command) {
    Set<ItemId> itemIds = command.orderItems().keySet().stream().map(ItemId::new).collect(Collectors.toSet());
    Rates rates = new Rates(inventoryService.getItemRates(itemIds));
    Billing billing = new Billing(billings.nextId(), command, rates);
    billings.save(billing);
  }
}
