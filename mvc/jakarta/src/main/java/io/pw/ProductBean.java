package io.pw;

import io.pw.model.Product;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pwykowski
 */

@SessionScoped
@Named
public class ProductBean implements Serializable {

	private List<Product> products = new ArrayList<>(List.of(
			new Product("PlayStation 4"),
			new Product("PlayStation 5"),
			new Product("Xbox One")
	));

	private Product newProduct = new Product(null);

	public List<Product> getProducts() {
		System.out.println("Getting products: " + this.products);
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}

	public void insert() {
		this.products.add(newProduct);
		newProduct = new Product(null);
	}

}
