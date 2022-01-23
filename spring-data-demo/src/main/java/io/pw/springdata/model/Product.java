package io.pw.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by pwykowski
 */
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private long id;
	private String name;
	private String description;
	@Column(name = "serial_number")
	private String serialNumber;
	private float price;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime created;

	public Product() {
	}

	public Product(String name, String description, String serialNumber, float price) {
		this.name = name;
		this.description = description;
		this.serialNumber = serialNumber;
		this.price = price;
	}

	@PrePersist
	protected void onCreate() {
		this.created = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
}
