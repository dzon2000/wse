package io.pw.springdata;

import io.pw.springdata.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pwykowski
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findByName(String name);
}
