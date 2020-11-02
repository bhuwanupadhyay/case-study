package io.github.bhuwanupadhyay.casestudy.inventory.infrastructure.repositories.mybatis.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemDto implements Serializable {

	private String itemName;

	private String itemDescription;

	private BigDecimal price;

	private Integer quantity;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemDto{" + "itemName='" + itemName + '\'' + ", itemDescription='" + itemDescription + '\'' + ", price="
				+ price + ", quantity=" + quantity + '}';
	}

}
