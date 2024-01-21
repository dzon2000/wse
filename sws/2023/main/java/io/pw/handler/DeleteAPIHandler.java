package io.pw.handler;

import com.sun.net.httpserver.HttpExchange;
import io.pw.db.Product;
import io.pw.db.ProductRepository;
import io.pw.http.QueryParamParser;
import io.pw.uri.Param;

import java.nio.charset.StandardCharsets;

/**
 * Created by pwykowski
 */
public class DeleteAPIHandler implements ResponseHandler {

	private final ProductRepository productRepository = new ProductRepository();
	private final HttpExchange request;

	public DeleteAPIHandler(HttpExchange request) {
		this.request = request;
	}

	@Override
	public byte[] handle() {
		long id = Long.parseLong(QueryParamParser.getParamsMap(request.getRequestURI().getQuery()).getOrDefault("id", new Param("id", "-1")).value());
		final Product product = productRepository.deleteById(id);
		return product.toJSON().toString().getBytes(StandardCharsets.UTF_8);
	}
}
