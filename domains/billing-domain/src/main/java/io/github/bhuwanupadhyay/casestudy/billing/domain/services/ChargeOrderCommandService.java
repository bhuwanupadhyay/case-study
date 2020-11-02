package io.github.bhuwanupadhyay.casestudy.billing.domain.services;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ChargeOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import io.github.bhuwanupadhyay.core.CommandService;

public class ChargeOrderCommandService implements CommandService<ChargeOrderCommand> {

	private final Billings billings;

	private final InventoryService inventoryService;

	public ChargeOrderCommandService(Billings billings, InventoryService inventoryService) {
		this.billings = billings;
		this.inventoryService = inventoryService;
	}

	@Override
	public void execute(ChargeOrderCommand command) {
		Rates rates = ServiceUtils.getRates(inventoryService, command.orderItems());
		Billing billing = new Billing(billings.nextId(), command, rates);
		billings.save(billing);
	}

}
