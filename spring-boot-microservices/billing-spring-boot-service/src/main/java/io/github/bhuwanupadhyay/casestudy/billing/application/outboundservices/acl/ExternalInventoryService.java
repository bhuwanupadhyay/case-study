package io.github.bhuwanupadhyay.casestudy.billing.application.outboundservices.acl;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Price;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.InventoryService;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.services.http.InventoryServiceClient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class ExternalInventoryService implements InventoryService {

    private final InventoryServiceClient inventoryServiceClient;

    public ExternalInventoryService(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Override
    public Map<ItemId, Price> getItemRates(Set<ItemId> itemIds) {
        return null;
    }

}
