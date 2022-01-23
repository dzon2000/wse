package io.pw.springdata;

import io.pw.springdata.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by pwykowski
 */
@Component
public class DatabaseLoader implements CommandLineRunner {
	private final ProductRepository productRepository;

	@Autowired
	public DatabaseLoader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void run(String... data) {
		final Product product = new Product("PlayStation", "Play Station 5", "123-123-123", 11.33f);
		productRepository.save(product);
	}

}
