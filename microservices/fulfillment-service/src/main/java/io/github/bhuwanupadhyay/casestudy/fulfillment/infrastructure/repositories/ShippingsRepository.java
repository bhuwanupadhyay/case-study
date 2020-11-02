package io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingAddress;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingStatus;
import io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.repositories.jdbc.ShippingEntity;
import io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.repositories.jdbc.ShippingEntityRepository;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShippingsRepository extends Shippings {

	private final ShippingEntityRepository repository;

	public ShippingsRepository(DomainEventPublisher publisher, ShippingEntityRepository repository) {
		super(publisher);
		this.repository = repository;
	}

	@Override
	public Optional<Shipping> findByOrderIdAndStatusNotShipped(OrderId orderId) {
		return repository.findByOrderIdAndStatusNot(orderId.value(), ShippingStatus.SHIPPED.name())
				.map(this::toShipping);
	}

	@Override
	public Optional<Shipping> findOne(ShippingId shippingId) {
		return repository.findByShippingId(shippingId.value()).map(this::toShipping);
	}

	@Override
	protected void persist(Shipping entity) {
		Optional<ShippingEntity> findOne = repository.findByShippingId(entity.getId().value());
		ShippingEntity db;
		if (findOne.isPresent()) {
			db = findOne.get();
		}
		else {
			entity.setCreatedAt(LocalDateTime.now());
			db = new ShippingEntity();
			db.setOrderId(entity.getOrderId().value());
			db.setShippingId(entity.getId().value());
			db.setCreatedAt(entity.getCreatedAt());
		}
		db.setStatus(entity.getStatus().name());
		db.setShippingAddress(entity.getShippingAddress().value());
		repository.save(db);
	}

	@Override
	public ShippingId nextId() {
		return new ShippingId(UUID.randomUUID().toString());
	}

	private Shipping toShipping(ShippingEntity e) {
		Shipping shipping = new Shipping(new ShippingId(e.getShippingId()), new OrderId(e.getOrderId()),
				new ShippingAddress(e.getShippingAddress()));
		shipping.setCreatedAt(e.getCreatedAt());
		return shipping.withStatus(ShippingStatus.valueOf(e.getStatus()));
	}

}
