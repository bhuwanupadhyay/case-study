package io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.repositories.jdbc;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("shippings")
public class ShippingEntity implements Serializable {

  @Id
  @Column("id")
  private Long id;
  @Column("shipping_id")
  private String shippingId;
  @Column("order_id")
  private String orderId;
  @Column("shipping_address")
  private String shippingAddress;
  @Column("created_at")
  private LocalDateTime createdAt;
  @Column("status")
  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShippingId() {
    return shippingId;
  }

  public void setShippingId(String shippingId) {
    this.shippingId = shippingId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShippingEntity that = (ShippingEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override public int hashCode() {
    return Objects.hash(id);
  }
}
