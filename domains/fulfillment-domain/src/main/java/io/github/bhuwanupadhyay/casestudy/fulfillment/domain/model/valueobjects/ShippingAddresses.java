package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects;

import java.util.Map;

public record ShippingAddresses(Map<CustomerId, ShippingAddress> value) {
  public ShippingAddress getByCustomerId(CustomerId customerId) {
    return this.value.get(customerId);
  }
}
