package io.pw.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pwykowski
 */
public class ProductRepository {

	public static List<Product> PRODUCTS = new ArrayList<>(List.of(
		new Product(1, "PlayStation 5", "With VR Set", "123-123", 50),
		new Product(2, "Xbox Series X", "With Forza Horizon 5", "155-123", 33),
		new Product(3, "Samsung LCD TV", "55'", "999-131", 5),
		new Product(4, "Sony LCD TV", "66'", "821-131", 5)
	));

}
