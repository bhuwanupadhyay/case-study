package io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.CancelOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.PlaceOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;

public class Order extends AggregateRoot<OrderId> {

  public Order(OrderId orderId) {
    super(orderId);
  }

  public Order(OrderId orderId, PlaceOrderCommand command) {
    this(orderId);
  }

  public void on(ModifyOrderCommand command) {}

  public void on(CancelOrderCommand command) {}
}
