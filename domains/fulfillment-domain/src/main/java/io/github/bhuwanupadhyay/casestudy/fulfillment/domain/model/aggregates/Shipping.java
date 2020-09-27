package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.PrepareShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ShipOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.events.OrderShipped;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.CustomerId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingAddress;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingAddresses;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingId;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.ShippingStatus;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;

public class Shipping extends AggregateRoot<ShippingId> {

  private final OrderId orderId;
  private ShippingStatus status;
  private ShippingAddress shippingAddress;

  public Shipping(ShippingId shippingId, PrepareShippingCommand command, ShippingAddresses shippingAddresses) {
    super(shippingId);
    this.shippingAddress = shippingAddresses.getByCustomerId(new CustomerId(command.orderId()));
    this.orderId = new OrderId(command.orderId());
    this.status = ShippingStatus.PREPARED;
  }

  public void on(ShipOrderCommand command) {
    this.status = ShippingStatus.SHIPPED;
    this.registerEvent(new OrderShipped(this.getId(), this.orderId, this.shippingAddress));
  }

  public OrderId getOrderId() {
    return orderId;
  }

  public ShippingStatus getStatus() {
    return status;
  }

  public ShippingAddress getShippingAddress() {
    return shippingAddress;
  }
}
