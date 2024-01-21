package io.pw.handler;

import com.sun.net.httpserver.HttpExchange;
import io.pw.db.Product;
import io.pw.db.ProductRepository;
import io.pw.http.QueryParamParser;
import io.pw.uri.Param;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Created by pwykowski
 */
public class GetAPIHandler implements ResponseHandler {

	private final ProductRepository productRepository = new ProductRepository();
	private final HttpExchange request;

	public GetAPIHandler(HttpExchange request) {
		this.request = request;
	}

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
		getProducts().forEach(product -> {
			products.put(product.toJSON());
		});
		data.put("products", products);
		return data.toString().getBytes(StandardCharsets.UTF_8);
	}

	private List<Product> getProducts() {
		final Map<String, Param> paramsMap = QueryParamParser.getParamsMap(request.getRequestURI().getQuery());
		if (paramsMap.containsKey("limit")) {
			return productRepository.findAllPaginate(
					Integer.parseInt(paramsMap.get("limit").value()),
					Integer.parseInt(paramsMap.getOrDefault("offset", new Param("offset", "0")).value()));
		}
		return productRepository.findAll();
	}
}
