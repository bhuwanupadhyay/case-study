package io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.ddd.AggregateRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;

public abstract class Billings extends AggregateRepository<Billing, BillingId> {
  protected Billings(DomainEventPublisher publisher) {
    super(publisher);
  }
}
