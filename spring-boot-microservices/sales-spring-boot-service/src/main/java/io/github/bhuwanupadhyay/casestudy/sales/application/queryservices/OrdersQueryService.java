package io.github.bhuwanupadhyay.casestudy.sales.application.queryservices;

import io.github.bhuwanupadhyay.casestudy.sales.domain.commands.OrderItem;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.SaleOrderLines;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.SaleOrders;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import static io.github.bhuwanupadhyay.casestudy.sales.interfaces.HttpHandler.OrderResource;
import static io.github.bhuwanupadhyay.casestudy.sales.jooq.Tables.SALE_ORDERS;
import static io.github.bhuwanupadhyay.casestudy.sales.jooq.Tables.SALE_ORDER_LINES;

@Service
public class OrdersQueryService {
  private static final SaleOrders ORDERS = SALE_ORDERS;
  private static final SaleOrderLines ORDER_LINES = SALE_ORDER_LINES;
  private final DSLContext context;

  public OrdersQueryService(DSLContext context) {
    this.context = context;
  }

  public OrderResource findByOrderId(String orderId) {
    Result<Record3<String, String, Integer>> result =
        this.context.select(ORDERS.ORDER_ID, ORDER_LINES.ITEM_ID, ORDER_LINES.QUANTITY)
            .from(ORDER_LINES)
            .join(ORDERS).on(ORDERS.ORDER_ID.eq(ORDER_LINES.ORDER_ID))
            .where(ORDERS.ORDER_ID.eq(orderId))
            .fetch();
    List<OrderItem> orderItems = new ArrayList<>();
    for (Record3<String, String, Integer> record3 : result) {
      orderItems.add(new OrderItem(record3.value2(), record3.value3()));
    }
    return new OrderResource(orderId, orderItems);
  }
}
