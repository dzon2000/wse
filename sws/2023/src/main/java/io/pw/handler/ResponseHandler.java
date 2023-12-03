package io.pw.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Created by pwykowski
 */
public interface ResponseHandler {

	static ResponseHandler ofPath(String path, HttpExchange request) {
		return switch (path) {
			case "/" -> new MainPageHandler();
			case "/add" -> new AddNewProductHandler(request.getRequestBody());
			case "/api/product" -> new APIHandler();
			default -> throw new RuntimeException("Handler not found");
		};
	}

	byte[] handle();

}
