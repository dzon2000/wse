package io.pw.handler;

import io.pw.db.Product;
import io.pw.db.ProductRepository;
import io.pw.http.FormBodyParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by pwykowski
 */
public class AddNewProductHandler implements ResponseHandler {

	private final InputStream requestBody;

	public AddNewProductHandler(InputStream requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public byte[] handle() {
		try {
			String body = URLDecoder.decode(new String(requestBody.readAllBytes()), StandardCharsets.UTF_8);
			final Product product = FormBodyParser.fromFormBody(body);
			ProductRepository.PRODUCTS.add(product);
			return "OK".getBytes(StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
