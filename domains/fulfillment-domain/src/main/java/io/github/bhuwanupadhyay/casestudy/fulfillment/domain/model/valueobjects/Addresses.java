package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects;

import java.util.Map;

public record Addresses(Map<CustomerId, ShippingAddress> value) {
  public ShippingAddress getByCustomerId(CustomerId customerId) {
    return new ShippingAddress("Lamki, Kailali");
  }
}
