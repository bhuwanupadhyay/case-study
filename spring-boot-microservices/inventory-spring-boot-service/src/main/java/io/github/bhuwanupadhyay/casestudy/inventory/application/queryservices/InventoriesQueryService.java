package io.github.bhuwanupadhyay.casestudy.inventory.application.queryservices;

import io.github.bhuwanupadhyay.casestudy.inventory.domain.InventoryId;
import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dao.InventoryDao;
import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto.ItemDto;
import io.github.bhuwanupadhyay.casestudy.inventory.interfaces.ItemResource;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class InventoriesQueryService {

  private final InventoryDao inventoryDao;

  public InventoriesQueryService(InventoryDao inventoryDao) {
    this.inventoryDao = inventoryDao;
  }

  public List<ItemResource> selectItems(InventoryId id) {
    List<ItemDto> list = inventoryDao.selectItems(id);
    return list.stream().map(ItemResource::new).collect(Collectors.toList());
  }
}
