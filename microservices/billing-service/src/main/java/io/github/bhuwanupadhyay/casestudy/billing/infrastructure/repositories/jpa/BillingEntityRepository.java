package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingEntityRepository extends JpaRepository<BillingEntity, Long> {

	Optional<BillingEntity> findByBillingId(String billingId);

	Optional<BillingEntity> findByOrderId(String orderId);

}
