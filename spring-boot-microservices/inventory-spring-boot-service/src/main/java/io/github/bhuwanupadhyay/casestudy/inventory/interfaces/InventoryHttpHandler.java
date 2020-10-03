package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import io.github.bhuwanupadhyay.casestudy.inventory.domain.Inventory;
import io.github.bhuwanupadhyay.casestudy.inventory.domain.InventoryId;
import java.util.Map;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface HttpHandler extends CrudRepository<Inventory, InventoryId> {


}
