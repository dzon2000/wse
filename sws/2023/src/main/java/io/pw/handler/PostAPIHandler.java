package io.pw.handler;

import com.sun.net.httpserver.HttpExchange;
import io.pw.db.Product;
import io.pw.db.ProductRepository;
import io.pw.http.QueryParamParser;
import io.pw.uri.Param;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by pwykowski
 */
public class PostAPIHandler implements ResponseHandler {

	private final ProductRepository productRepository = new ProductRepository();
	private final HttpExchange request;

	public PostAPIHandler(HttpExchange request) {
		this.request = request;
	}

	@Override
	public byte[] handle() {
		final Map<String, Param> paramsMap = QueryParamParser.getParamsMap(request.getRequestURI().getQuery());
		final Product product = new Product(
				-1,
				paramsMap.get("name").value(),
				paramsMap.get("desc").value(),
				paramsMap.get("serial").value(),
				Integer.parseInt(paramsMap.get("qty").value())
		);
		productRepository.store(product);
		return """
				{
					"inserted": 1
				}
				""".getBytes(StandardCharsets.UTF_8);
	}
}
