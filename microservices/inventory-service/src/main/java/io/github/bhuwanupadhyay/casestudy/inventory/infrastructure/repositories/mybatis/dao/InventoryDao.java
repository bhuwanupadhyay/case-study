package io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dao;

import io.github.bhuwanupadhyay.casestudy.inventory.domain.InventoryId;
import io.github.bhuwanupadhyay.casestudy.inventory.domain.ItemId;
import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto.ItemDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryDao {

	private final SqlSession sqlSession;

	public InventoryDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<ItemDto> selectItems() {
		return this.sqlSession.selectList("selectItems");
	}

	public List<ItemDto> selectItems(InventoryId inventoryId) {
		return this.sqlSession.selectList("selectItemsByInventoryId", inventoryId.value());
	}

	public Optional<ItemDto> selectItem(ItemId itemId) {
		return Optional.ofNullable(this.sqlSession.selectOne("selectItemByItemId", itemId.value()));
	}

}
