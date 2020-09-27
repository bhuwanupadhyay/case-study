package io.github.bhuwanupadhyay.casestudy.sales.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository extends Orders {

  private final JdbcTemplate jdbc;

  protected OrdersRepository(DomainEventPublisher publisher, JdbcTemplate jdbc) {
    super(publisher);
    this.jdbc = jdbc;
  }

  @Override public Optional<Order> findOne(OrderId orderId) {
    return Optional.empty();
  }

  @Override protected void persist(Order entity) {

  }

  @Override public OrderId nextId() {
    return null;
  }
}
