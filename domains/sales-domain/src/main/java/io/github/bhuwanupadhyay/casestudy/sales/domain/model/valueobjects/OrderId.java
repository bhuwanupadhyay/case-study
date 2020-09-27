package io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects;

import io.github.bhuwanupadhyay.core.Problem;
import io.github.bhuwanupadhyay.ddd.ValueObject;

public record OrderId(String value) implements ValueObject {

  public static final String ORDER_ID_INVALID = "orderId.invalid";

  //public OrderId {
  //  Problem.raiseIfEmpty(this.value(), () -> ORDER_ID_INVALID);
  //}
}
