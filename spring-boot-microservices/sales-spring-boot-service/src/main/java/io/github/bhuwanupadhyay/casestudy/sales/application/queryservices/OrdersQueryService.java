package io.github.bhuwanupadhyay.casestudy.sales.application.queryservices;

import java.util.HashMap;
import org.springframework.stereotype.Service;

import static io.github.bhuwanupadhyay.casestudy.sales.interfaces.HttpHandler.*;

@Service
public class OrdersQueryService {

  public OrderResource findByOrderId(String orderId) {
    return new OrderResource("", new HashMap<>());
  }
}
