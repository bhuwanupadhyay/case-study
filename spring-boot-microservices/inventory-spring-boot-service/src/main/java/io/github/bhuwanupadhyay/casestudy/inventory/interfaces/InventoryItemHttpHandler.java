package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import io.github.bhuwanupadhyay.casestudy.inventory.domain.Inventory;
import io.github.bhuwanupadhyay.casestudy.inventory.domain.InventoryId;
import org.springframework.data.repository.CrudRepository;

public interface InventoryHttpHandler extends CrudRepository<Inventory, InventoryId> {

}
