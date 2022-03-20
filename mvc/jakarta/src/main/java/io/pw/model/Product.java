package io.pw.model;

import jakarta.enterprise.inject.Model;

/**
 * Created by pwykowski
 */
@Model
public class Product {

	private String name;

	public Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product{" +
			   "name='" + name + '\'' +
			   '}';
	}
}
