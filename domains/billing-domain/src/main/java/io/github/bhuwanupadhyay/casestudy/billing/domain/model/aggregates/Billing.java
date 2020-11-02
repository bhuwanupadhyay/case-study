package io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ChargeOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.ModifyChargeCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.billing.domain.commands.RefundOrderCommand;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.ModificationBilled;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.OrderBilled;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.events.OrderRefunded;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.*;
import io.github.bhuwanupadhyay.core.BadRequestException;
import io.github.bhuwanupadhyay.core.Problem;
import io.github.bhuwanupadhyay.core.SimpleProblem;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;

import java.math.BigDecimal;
import java.util.List;

public class Billing extends AggregateRoot<BillingId> {

	private final OrderId orderId;

	private BillAmount billAmount;

	private BillingStatus status;

	private RefundReason refundReason;

	public Billing(BillingId billingId, OrderId orderId) {
		super(billingId);
		this.orderId = orderId;
	}

	public Billing(BillingId billingId, ChargeOrderCommand command, Rates rates) {
		super(billingId);
		this.billAmount = getTotalPrice(rates, command.orderItems());
		this.orderId = new OrderId(command.orderId());
		this.status = BillingStatus.CHARGED;
		this.registerEvent(new OrderBilled(this.getId(), this.orderId, this.billAmount));
	}

	private static BillAmount getTotalPrice(Rates rates, List<OrderItem> orderItems) {
		BigDecimal amount = BigDecimal.ZERO;
		for (OrderItem e : orderItems) {
			ItemId key = new ItemId(e.itemId());
			Price price = rates.getByItemId(key).orElseThrow(
					() -> new BadRequestException(new SimpleProblem("orderItems." + key, "price.not.found")));
			Quantity quantity = new Quantity(e.quantity());
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

	public Billing withAmount(BillAmount billAmount) {
		Problem.notNull(billAmount);
		this.billAmount = billAmount;
		return this;
	}

	public Billing withRefundReason(RefundReason refundReason) {
		this.refundReason = refundReason;
		return this;
	}

	public Billing withStatus(BillingStatus status) {
		Problem.notNull(status);
		this.status = status;
		return this;
	}

}
