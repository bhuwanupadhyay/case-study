package io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.CancelOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.PlaceOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.events.OrderCancelled;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.events.OrderModified;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.events.OrderPlaced;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.CancellationReason;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.CustomerId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderStatus;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.Quantity;
import io.github.bhuwanupadhyay.core.Problem;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Order extends AggregateRoot<OrderId> {

  private final Map<ItemId, Quantity> orderLines = new LinkedHashMap<>();
  private CustomerId customerId;
  private OrderStatus status;
  private CancellationReason cancellationReason;

  public Order(OrderId orderId) {
    super(orderId);
  }

  public Order(OrderId orderId, PlaceOrderCommand command) {
    this(orderId);
    this.addOrderItems(command.orderItems());
    this.customerId = new CustomerId(command.customerId());
    this.status = OrderStatus.PLACED;
    this.registerEvent(new OrderPlaced(this.getId(), this.orderLines));
  }

  public void on(ModifyOrderCommand command) {
    this.status = OrderStatus.MODIFIED;
    this.addOrderItems(command.orderItems());
    this.registerEvent(new OrderModified(this.getId(), this.orderLines));
  }

  private void addOrderItems(List<OrderItem> orderItems) {
    for (OrderItem e : orderItems) {
      orderLines.put(new ItemId(e.itemId()), new Quantity(e.quantity()));
    }
  }

  public void on(CancelOrderCommand command) {
    this.cancellationReason = new CancellationReason(command.reason());
    this.status = OrderStatus.CANCELLED;
    this.registerEvent(new OrderCancelled(this.getId(), this.cancellationReason));
  }

  public OrderStatus getStatus() {
    return status;
  }

  public CustomerId getCustomerId() {
    return customerId;
  }

  public Map<ItemId, Quantity> getOrderLines() {
    return Collections.unmodifiableMap(this.orderLines);
  }

  public CancellationReason getCancellationReason() {
    return cancellationReason;
  }

  public Order withCancellationReason(CancellationReason reason) {
    this.cancellationReason = reason;
    return this;
  }

  public Order withStatus(OrderStatus status) {
    Problem.notNull(status);
    this.status = status;
    return this;
  }

  public Order withCustomerId(CustomerId customerId) {
    Problem.notNull(customerId);
    this.customerId = customerId;
    return this;
  }

  public Order withItem(ItemId itemId, Quantity quantity) {
    Problem.notNull(itemId);
    Problem.notNull(quantity);
    this.orderLines.put(itemId, quantity);
    return this;
  }
}
