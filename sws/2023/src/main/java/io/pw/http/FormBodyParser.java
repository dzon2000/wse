package io.pw.http;

import io.pw.db.Product;
import io.pw.db.ProductRepository;

/**
 * Created by pwykowski
 */
public class FormBodyParser {

	public static Product fromFormBody(String body) {
		final String[] split = body.split("&");
		return new Product(
				ProductRepository.PRODUCTS.size() + 1,
				split[0].split("=")[1],
				split[1].split("=")[1],
				split[2].split("=")[1],
				Integer.parseInt(split[3].split("=")[1])
		);
	}

}
