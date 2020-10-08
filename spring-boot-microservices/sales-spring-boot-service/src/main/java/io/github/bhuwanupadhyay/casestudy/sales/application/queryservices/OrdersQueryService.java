package io.github.bhuwanupadhyay.casestudy.sales.application.queryservices;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.sales.interfaces.HttpHandler.OrderItemResource;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.SaleOrderLines;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.SaleOrders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import static io.github.bhuwanupadhyay.casestudy.sales.interfaces.HttpHandler.OrderResource;
import static io.github.bhuwanupadhyay.casestudy.sales.jooq.Tables.SALE_ORDERS;
import static io.github.bhuwanupadhyay.casestudy.sales.jooq.Tables.SALE_ORDER_LINES;

@Service
public record OrdersQueryService(DSLContext context) {
  private static final SaleOrders ORDERS = SALE_ORDERS;
  private static final SaleOrderLines ORDER_LINES = SALE_ORDER_LINES;

  public OrderResource findByOrderId(String orderId) {
    Result<Record3<String, String, Integer>> orderById =
        this.context.select(ORDERS.ORDER_ID, ORDER_LINES.ITEM_ID, ORDER_LINES.QUANTITY)
            .from(ORDER_LINES)
            .join(ORDERS).on(ORDERS.ORDER_ID.eq(ORDER_LINES.ORDER_ID))
            .where(ORDERS.ORDER_ID.eq(orderId))
            .fetch();
    List<OrderItemResource> orderItems = new ArrayList<>();
    for (Record3<String, String, Integer> record3 : orderById) {
      orderItems.add(new OrderItemResource(record3.value2(), record3.value3()));
    }
    return new OrderResource(orderId, orderItems);
  }

  public List<OrderResource> findAll() {
    Map<String, List<Record3<String, String, Integer>>> groupByOrderId =
        this.context.select(ORDERS.ORDER_ID, ORDER_LINES.ITEM_ID, ORDER_LINES.QUANTITY)
            .from(ORDER_LINES)
            .join(ORDERS).on(ORDERS.ORDER_ID.eq(ORDER_LINES.ORDER_ID))
            .fetch()
            .stream()
            .collect(Collectors.groupingBy(Record3::value1));

    List<OrderResource> result = new ArrayList<>();

    for (Map.Entry<String, List<Record3<String, String, Integer>>> e : groupByOrderId.entrySet()) {
      List<OrderItemResource> orderItems = new ArrayList<>();
      for (Record3<String, String, Integer> record3 : e.getValue()) {
        orderItems.add(new OrderItemResource(record3.value2(), record3.value3()));
      }
      result.add(new OrderResource(e.getKey(), orderItems));
    }
    return result;
  }
}
