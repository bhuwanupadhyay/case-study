package io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingsRepository extends Shippings {
  protected ShippingsRepository(DomainEventPublisher publisher) {
    super(publisher);
  }

  @Override public Shipping findByOrderId(OrderId orderId) {
    return null;
  }

  @Override public Optional<Shipping> findOne(ShippingId shippingId) {
    return Optional.empty();
  }

  @Override protected void persist(Shipping entity) {

  }

  @Override public ShippingId nextId() {
    return null;
  }
}
