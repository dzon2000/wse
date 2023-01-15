package io.pw.response;

import io.pw.db.ProductRepository;
import io.pw.db.Repository;
import io.pw.db.entity.Product;
import io.pw.util.QueryParamParser;
import org.json.JSONObject;

import java.util.Optional;

/**
 * Created by pwykowski
 */
public class ReduceHandler implements ResponseHandler {

	private final String query;
	private final Repository<Product> repository = new ProductRepository();

	public ReduceHandler(String query) {
		this.query = query;
	}

	@Override
	public byte[] handle() {
		final JSONObject params = QueryParamParser.parseQueryString(query);
		long id = Long.parseLong(params.optString("id", "-1"));
		final Optional<Product> optionalProduct = repository.findById(id);
		if (optionalProduct.isEmpty())
			return new byte[0];
		final Product product = optionalProduct.get();
		if (product.getQty() == 1) {
			return "Quantity too low".getBytes();
		}
		final int newQty = product.getQty() - 1;
		repository.update(new Product(
				product.getId(),
				product.getName(),
				product.getDesc(),
				product.getSerial(),
				newQty
		));
		return String.valueOf(newQty).getBytes();
	}
}
