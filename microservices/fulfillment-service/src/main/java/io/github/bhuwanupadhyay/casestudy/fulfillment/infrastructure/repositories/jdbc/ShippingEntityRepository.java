package io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.repositories.jdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShippingEntityRepository extends CrudRepository<ShippingEntity, Long> {

	Optional<ShippingEntity> findByOrderIdAndStatusNot(String orderId, String status);

	Optional<ShippingEntity> findByShippingId(String shippingId);

}
