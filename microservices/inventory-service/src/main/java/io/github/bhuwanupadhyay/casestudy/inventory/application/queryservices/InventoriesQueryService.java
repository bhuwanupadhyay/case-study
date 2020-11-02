package io.github.bhuwanupadhyay.casestudy.inventory.application.queryservices;

import io.github.bhuwanupadhyay.casestudy.inventory.domain.ItemId;
import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dao.InventoryDao;
import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto.ItemDto;
import io.github.bhuwanupadhyay.casestudy.inventory.interfaces.ItemResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoriesQueryService {

	private final InventoryDao inventoryDao;

	public InventoriesQueryService(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public List<ItemResource> selectItems() {
		List<ItemDto> list = inventoryDao.selectItems();
		return list.stream().map(ItemResource::new).collect(Collectors.toList());
	}

	public Optional<ItemDto> selectItem(ItemId itemId) {
		return inventoryDao.selectItem(itemId);
	}

}
