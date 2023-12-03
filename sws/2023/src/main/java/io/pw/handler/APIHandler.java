package io.pw.handler;

import io.pw.db.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * Created by pwykowski
 */
public class APIHandler implements ResponseHandler {

	private ProductRepository productRepository = new ProductRepository();

	/*

		{
			"products": [
				{
					"id": 1,
					"name": "PS"
					...
				}
			]
		}

	 */

	@Override
	public byte[] handle() {
		JSONObject data = new JSONObject();
		JSONArray products = new JSONArray();
		productRepository.findAll().forEach(product -> {
			JSONObject productJSON = new JSONObject();
			productJSON.put("id", product.id());
			productJSON.put("name", product.name());
			productJSON.put("desc", product.desc());
			productJSON.put("serial", product.serialNumber());
			productJSON.put("quantity", product.quantity());
			products.put(productJSON);
		});
		data.put("products", products);
		return data.toString().getBytes(StandardCharsets.UTF_8);
	}
}
