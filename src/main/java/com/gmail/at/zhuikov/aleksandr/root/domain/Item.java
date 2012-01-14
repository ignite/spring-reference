package com.gmail.at.zhuikov.aleksandr.root.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.style.ToStringCreator;


/**
 * An item in an order
 */
@Entity
@GroupSequence({ Item.class, ComplexValidation.class })
@UniqueProductInOrder(groups = ComplexValidation.class)
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Order order;

	private String product;

	private double price;

	private int quantity;

	protected Item() {
	}
	
	public Item(Order order, String product, double price) {
		this.order = order;
		this.product = product;
		this.price = price;
		order.getItems().add(this);
	}

	/**
	 * @return the order
	 */
	@Valid
	public Order getOrder() {
		return order;
	}

	/**
	 * @return the product
	 */
	@NotBlank
	public String getProduct() {
		return product;
	}

	/**
	 * @return the price
	 */
	@Min(0)
	public double getPrice() {
		return price;
	}

	/**
	 * @return the quantity
	 */
	@Min(1)
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("product", product)
				.append("quantity", quantity)
				.append("price", price)
				.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		
		if (obj == this) {
			return true;
		}
		
		if (obj.getClass() != getClass()) {
			return false;
		}

		Item other = (Item) obj;

		return new EqualsBuilder()
				.append(product, other.product)
				.append(order, other.order)
				.append(price, other.price)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(product)
				.append(order)
				.append(price)
				.toHashCode();
	}
}

interface ComplexValidation {}