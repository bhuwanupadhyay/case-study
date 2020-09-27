package io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.PlaceOrderCommand;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void canPlaceOrder() {
    Order order = new Order(new OrderId("123"), new PlaceOrderCommand());
  }
}
