package io.github.bhuwanupadhyay.casestudy.sales.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.sales.domain.model.aggregates.Order;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.CancellationReason;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.CustomerId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.ItemId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.OrderStatus;
import io.github.bhuwanupadhyay.casestudy.sales.domain.model.valueobjects.Quantity;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.SaleOrderLines;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.SaleOrders;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.records.SaleOrderLinesRecord;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.records.SaleOrdersRecord;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import static io.github.bhuwanupadhyay.casestudy.sales.jooq.Tables.SALE_ORDERS;
import static io.github.bhuwanupadhyay.casestudy.sales.jooq.Tables.SALE_ORDER_LINES;

@Repository
public class OrdersRepository extends Orders {

  private static final SaleOrders ORDERS = SALE_ORDERS;
  private static final SaleOrderLines ORDER_LINES = SALE_ORDER_LINES;
  private final DSLContext context;

  protected OrdersRepository(DomainEventPublisher publisher, DSLContext context) {
    super(publisher);
    this.context = context;
  }

  @Override public Optional<Order> findOne(OrderId orderId) {
    SaleOrdersRecord record = context.selectFrom(SALE_ORDERS)
        .where(ORDERS.ORDER_ID.eq(orderId.value()))
        .fetchOne();
    return Optional.of(record).map(this::toOrder);
  }

  @Override protected void persist(Order entity) {
    int count = context.fetchCount(ORDERS, ORDERS.ORDER_ID.eq(entity.getId().value()));
    if (count == 0) {
      entity.setCreatedAt(LocalDateTime.now());
      context.insertInto(SALE_ORDERS)
          .set(ORDERS.ORDER_ID, entity.getId().value())
          .set(ORDERS.CUSTOMER_ID, entity.getCustomerId().value())
          .set(ORDERS.STATUS, entity.getStatus().name())
          .set(ORDERS.CREATED_AT, entity.getCreatedAt())
          .execute();
      for (Map.Entry<ItemId, Quantity> e : entity.getOrderLines().entrySet()) {
        context.insertInto(ORDER_LINES)
            .set(ORDER_LINES.ITEM_ID, e.getKey().value())
            .set(ORDER_LINES.QUANTITY, e.getValue().value())
            .set(ORDER_LINES.ORDER_ID, entity.getId().value())
            .execute();
      }
    } else {
      context.update(ORDERS)
          .set(ORDERS.STATUS, entity.getStatus().name())
          .set(ORDERS.CANCELLATION_REASON, entity.getCancellationReason().value())
          .where(ORDERS.ORDER_ID.eq(entity.getId().value()))
          .execute();
      for (Map.Entry<ItemId, Quantity> e : entity.getOrderLines().entrySet()) {
        int itemCount =
            context.fetchCount(ORDER_LINES, ORDER_LINES.ORDER_ID.eq(entity.getId().value()),
                ORDER_LINES.ITEM_ID.eq(e.getKey().value()));
        if (itemCount == 0) {
          context.insertInto(ORDER_LINES)
              .set(ORDER_LINES.ITEM_ID, e.getKey().value())
              .set(ORDER_LINES.QUANTITY, e.getValue().value())
              .set(ORDER_LINES.ORDER_ID, entity.getId().value())
              .execute();
        } else {
          context.update(ORDER_LINES)
              .set(ORDER_LINES.QUANTITY, e.getValue().value())
              .where(ORDER_LINES.ORDER_ID.eq(entity.getId().value()),
                  ORDER_LINES.ITEM_ID.eq(e.getKey().value()))
              .execute();
        }
      }
    }
  }

  @Override public OrderId nextId() {
    return new OrderId(UUID.randomUUID().toString());
  }

  private Order toOrder(SaleOrdersRecord record) {

    Order order = new Order(new OrderId(record.get(ORDERS.ORDER_ID)))
        .withCustomerId(new CustomerId(record.get(ORDERS.CUSTOMER_ID)))
        .withStatus(OrderStatus.valueOf(record.get(ORDERS.STATUS)))
        .withCancellationReason(
            new CancellationReason(record.get(ORDERS.CANCELLATION_REASON)));

    order.setCreatedAt(record.get(ORDERS.CREATED_AT));

    Result<SaleOrderLinesRecord> result = context.selectFrom(ORDER_LINES)
        .where(ORDER_LINES.ORDER_ID.eq(order.getId().value()))
        .fetch();

    for (SaleOrderLinesRecord linesRecord : result) {
      order.withItem(
          new ItemId(linesRecord.get(ORDER_LINES.ITEM_ID)),
          new Quantity(linesRecord.get(ORDER_LINES.QUANTITY))
      );
    }

    return order;
  }
}
