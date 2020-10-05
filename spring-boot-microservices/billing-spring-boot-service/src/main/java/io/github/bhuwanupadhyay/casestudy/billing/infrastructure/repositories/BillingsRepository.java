package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class BillingsRepository extends Billings {

  protected BillingsRepository(DomainEventPublisher publisher) {
    super(publisher);
  }

  @Override public Optional<Billing> findOne(BillingId billingId) {
    return Optional.empty();
  }

  @Override protected void persist(Billing entity) {

  }

  @Override public BillingId nextId() {
    return null;
  }
}
