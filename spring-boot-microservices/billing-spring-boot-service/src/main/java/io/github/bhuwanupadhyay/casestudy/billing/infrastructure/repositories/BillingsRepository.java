package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillAmount;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingStatus;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.RefundReason;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories.jpa.BillingEntity;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories.jpa.BillingEntityRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class BillingsRepository extends Billings {

  private final BillingEntityRepository repository;

  protected BillingsRepository(DomainEventPublisher publisher, BillingEntityRepository repository) {
    super(publisher);
    this.repository = repository;
  }

  @Override
  public Optional<Billing> findOne(BillingId billingId) {
    return repository.findByBillingId(billingId.value())
        .map(e -> {
          Billing rs = new Billing(new BillingId(e.getBillingId()), new OrderId(e.getOrderId()));
          rs.setCreatedAt(e.getCreatedAt());
          return rs.withAmount(new BillAmount(e.getBillAmount()))
              .withStatus(BillingStatus.valueOf(e.getStatus()))
              .withRefundReason(new RefundReason(e.getRefundReason()));
        });
  }

  @Override
  protected void persist(Billing entity) {
    Optional<BillingEntity> findOne = repository.findByBillingId(entity.getId().value());
    BillingEntity db;
    if (findOne.isPresent()) {
      db = findOne.get();
    } else {
      entity.setCreatedAt(LocalDateTime.now());
      db = new BillingEntity();
      db.setOrderId(entity.getOrderId().value());
      db.setBillingId(entity.getId().value());
      db.setCreatedAt(entity.getCreatedAt());
    }
    db.setStatus(entity.getStatus().name());
    db.setBillAmount(entity.getBillAmount().amount());
    db.setRefundReason(
        Optional.ofNullable(entity.getRefundReason()).map(RefundReason::value).orElse(null));
    repository.save(db);
  }

  @Override
  public BillingId nextId() {
    return new BillingId(UUID.randomUUID().toString());
  }
}
