package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingEntityRepository extends JpaRepository<BillingEntity, Long> {

  Optional<BillingEntity> findByBillingId(String billingId);

  Optional<BillingEntity> findByOrderId(String orderId);
}
