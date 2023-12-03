package io.pw;

import com.sun.net.httpserver.HttpServer;
import io.pw.handler.ResponseHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Created by pwykowski
 */
public class WarehouseApplication {

	public static final String CONTENT_ROOT = "src/main/resources";

	public static void main(String[] args) throws IOException {
		final HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

		httpServer.createContext("/", (httpExchange) -> {
			final ResponseHandler handler = ResponseHandler.ofPath("/", httpExchange);
			final byte[] response = handler.handle();
			final OutputStream os = httpExchange.getResponseBody();
			httpExchange.sendResponseHeaders(200, response.length);
			os.write(response);
			os.close();
		});

		httpServer.createContext("/add", (httpExchange) -> {
			final ResponseHandler handler = ResponseHandler.ofPath("/add", httpExchange);

			final byte[] response = handler.handle();
			httpExchange.getResponseHeaders().set("Location", "/");
			httpExchange.sendResponseHeaders(302, response.length);
			final OutputStream os = httpExchange.getResponseBody();
			os.write(response);
			os.close();
		});

		httpServer.createContext("/api/product", (httpExchange) -> {
			final ResponseHandler handler = ResponseHandler.ofPath("/api/product", httpExchange);
			httpExchange.getResponseHeaders().set("Content-Type", "application/json");
			final OutputStream os = httpExchange.getResponseBody();
			final byte[] response = handler.handle();
			httpExchange.sendResponseHeaders(200, response.length);
			os.write(response);
			os.close();
		});

		httpServer.start();
	}

}
