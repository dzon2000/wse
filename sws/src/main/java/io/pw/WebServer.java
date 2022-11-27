package io.pw;

import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by pwykowski
 */
public class WebServer {

	private static JSONArray PRODUCTS = new JSONArray();

	public static void main(String[] args) {
		JSONObject product = new JSONObject("""
				{
					"name": "Xbox",
					"qty": 100
				}
				""");
		PRODUCTS.put(product);

		try {
			final HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

			httpServer.createContext("/", (httpExchange) -> {
				String response = """
    					<html>
    						<body>
    							<h1>Welcome to my Web Server!</h1>
    						</body>
    					</html>
    					""";

				httpExchange.sendResponseHeaders(200, response.length());

				final OutputStream os = httpExchange.getResponseBody();
				os.write(response.getBytes());
				os.close();
			});

			httpServer.createContext("/api/products", (httpExchange) -> {
				final String requestMethod = httpExchange.getRequestMethod();
				String response = "OK";
				if ("GET".equals(requestMethod)) {
					response = new JSONObject().put("products", PRODUCTS).toString();
					httpExchange.getResponseHeaders().set("Content-Type", "application/json");
				} else if ("POST".equals(requestMethod)) {
					final InputStream requestBody = httpExchange.getRequestBody();
					String requestProduct =  new String(requestBody.readAllBytes());
					PRODUCTS.put(new JSONObject(requestProduct));
					requestBody.close();
				}
				httpExchange.sendResponseHeaders(200, response.length());
				final OutputStream os = httpExchange.getResponseBody();
				os.write(response.getBytes());
				os.close();
			});

			httpServer.start();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
