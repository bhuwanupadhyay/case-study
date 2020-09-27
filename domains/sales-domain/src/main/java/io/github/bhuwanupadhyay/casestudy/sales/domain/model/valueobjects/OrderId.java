package io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects;

import io.github.bhuwanupadhyay.core.Problem;
import io.github.bhuwanupadhyay.ddd.ValueObject;

public record OrderId(String id) implements ValueObject {

  public static final String ORDER_ID_INVALID = "orderId.invalid";

  public OrderId {
    Problem.raiseIfEmpty(id, () -> ORDER_ID_INVALID);
  }
}
