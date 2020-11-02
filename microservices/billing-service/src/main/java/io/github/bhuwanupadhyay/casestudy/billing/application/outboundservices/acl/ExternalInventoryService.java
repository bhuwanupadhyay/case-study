package io.github.bhuwanupadhyay.casestudy.billing.application.outboundservices.acl;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemPriceInfo;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Price;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.InventoryService;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.services.http.InventoryServiceClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalInventoryService implements InventoryService {

	private final InventoryServiceClient inventoryServiceClient;

	public ExternalInventoryService(InventoryServiceClient inventoryServiceClient) {
		this.inventoryServiceClient = inventoryServiceClient;
	}

	@Override
	@Async
	public CompletableFuture<ItemPriceInfo> getItemPrice(ItemId itemId) {
		Price price = new Price(inventoryServiceClient.getItemInfo(itemId.value()).price());
		return CompletableFuture.completedFuture(new ItemPriceInfo(itemId, price));
	}

}
