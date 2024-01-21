package io.pw;

import com.sun.net.httpserver.HttpServer;
import io.pw.db.DBConnection;
import io.pw.handler.ResponseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by pwykowski
 */
public class WarehouseApplication {

	// https://github.com/dzon2000/wse
	private static final Logger logger = LogManager.getLogger(WarehouseApplication.class);
	public static final String CONTENT_ROOT = "src/main/resources";

	public static void main(String[] args) {
		DBConnection.initDB();
		logger.info("Starting HTTP server on port 8000...");
		try {
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
				logger.info("Got request for /add with HTTP method: " + httpExchange.getRequestMethod());

				final ResponseHandler handler = ResponseHandler.ofPath("/add", httpExchange);
				final byte[] response = handler.handle();
				if ("POST".equals(httpExchange.getRequestMethod())) {
					httpExchange.getResponseHeaders().set("Location", "/");
					httpExchange.sendResponseHeaders(302, response.length);
				} else {
					httpExchange.sendResponseHeaders(200, response.length);
				}
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
		} catch (IOException e) {
			logger.error("Could not bind to port 8000", e);
		} catch (RuntimeException e) {
			logger.error("Could not process the request", e);
		}
	}

}
