package io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dao;

import io.github.bhuwanupadhyay.casestudy.inventory.domain.InventoryId;
import io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto.ItemDto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class InventoryDao {
  private final SqlSession sqlSession;

  public InventoryDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public List<ItemDto> selectItems(InventoryId id) {
    return this.sqlSession.selectList("selectItems", id.value());
  }
}
