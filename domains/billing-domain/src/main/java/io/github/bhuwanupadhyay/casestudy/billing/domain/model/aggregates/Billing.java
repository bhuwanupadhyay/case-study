package io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ChargeOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ModifyChargeCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.RefundOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.ModificationBilled;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.OrderBilled;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.OrderRefunded;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillAmount;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.BillingStatus;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Price;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Quantity;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.Rates;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.RefundReason;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;
import java.math.BigDecimal;
import java.util.Map;

public class Billing extends AggregateRoot<BillingId> {

  private final OrderId orderId;
  private BillAmount billAmount;
  private BillingStatus status;
  private RefundReason refundReason;

  public Billing(BillingId billingId, ChargeOrderCommand command, Rates rates) {
    super(billingId);
    this.billAmount = getTotalPrice(rates, command.orderItems());
    this.orderId = new OrderId(command.orderId());
    this.status = BillingStatus.CHARGED;
    this.registerEvent(new OrderBilled(this.getId(), this.orderId, this.billAmount));
  }

  private static BillAmount getTotalPrice(Rates rates, Map<String, Integer> orderItems) {
    BigDecimal amount = BigDecimal.ZERO;
    for (Map.Entry<String, Integer> e : orderItems.entrySet()) {
      ItemId key = new ItemId(e.getKey());
      Price price = rates.getByItemId(key);
      Quantity quantity = new Quantity(e.getValue());
      BigDecimal totalQuantityAmount = price.value().multiply(new BigDecimal(quantity.value()));
      amount = amount.add(totalQuantityAmount);
    }
    return new BillAmount(amount);
  }

  public void on(ModifyChargeCommand command, Rates rates) {
    this.billAmount = getTotalPrice(rates, command.orderItems());
    this.status = BillingStatus.CHARGED;
    this.registerEvent(new ModificationBilled(this.getId(), this.orderId, this.billAmount));
  }

  public void on(RefundOrderCommand command) {
    this.refundReason = new RefundReason(command.reason());
    this.status = BillingStatus.REFUNDED;
    this.registerEvent(new OrderRefunded(this.getId(), this.refundReason));
  }

  public RefundReason getRefundReason() {
    return refundReason;
  }

  public BillAmount getBillAmount() {
    return billAmount;
  }

  public OrderId getOrderId() {
    return orderId;
  }

  public BillingStatus getStatus() {
    return status;
  }
}
