package io.pw.handler;

import com.sun.net.httpserver.HttpExchange;

/**
 * Created by pwykowski
 */
public interface ResponseHandler {

	static ResponseHandler ofPath(String path, HttpExchange request) {
		return switch (path) {
			case "/" -> new MainPageHandler();
			case "/add" -> {
				if ("POST".equals(request.getRequestMethod()))
					yield new AddNewProductHandler(request.getRequestBody());
				else
					yield new AddProductPageHandler();
			}
			case "/api/product" -> {
				if ("POST".equals(request.getRequestMethod()))
					yield new PostAPIHandler(request);
				else if ("DELETE".equals(request.getRequestMethod()))
					yield new DeleteAPIHandler(request);
				else
					yield new GetAPIHandler(request);
			}
			default -> throw new RuntimeException("Handler not found");
		};
	}

	byte[] handle();

}
