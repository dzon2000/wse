package io.pw;

import com.sun.net.httpserver.HttpServer;
import io.pw.response.ResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by pwykowski
 */
public class WebServer {

    // https://github.com/dzon2000/wse

    public static JSONArray PRODUCTS = new JSONArray();

    public static String CONTENT_ROOT = "sws/src/main/resources";

    public static void main(String[] args) {
        JSONObject product = new JSONObject("""
                {
                	"name": "Xbox",
                	"desc": "This is the Xbox Series X",
                	"serial": "1234-567",
                	"qty": 100
                }
                """);
        PRODUCTS.put(product);

        try {
            final HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

            httpServer.createContext("/", (httpExchange) -> {
				ResponseHandler responseHandler = ResponseHandler.ofPath(httpExchange);
				byte[] response = responseHandler.handle();
				final OutputStream os = httpExchange.getResponseBody();
                httpExchange.sendResponseHeaders(200, response.length);
                os.write(response);
                os.close();
            });

            httpServer.createContext("/api/products", (httpExchange) -> {
                byte[] response = ResponseHandler.ofPath(httpExchange).handle();
                httpExchange.sendResponseHeaders(200, response.length);
                final OutputStream os = httpExchange.getResponseBody();
                os.write(response);
                os.close();
            });

            httpServer.createContext("/add", (httpExchange) -> {
                byte[] response = ResponseHandler.ofPath(httpExchange).handle();
                String requestMethod = httpExchange.getRequestMethod();
                if ("GET".equals(requestMethod)) {
                    httpExchange.sendResponseHeaders(200, response.length);
                } else {
                    httpExchange.getResponseHeaders().set("Location", "/");
                    httpExchange.sendResponseHeaders(302, response.length);
                }
                final OutputStream os = httpExchange.getResponseBody();
                os.write(response);
                os.close();
            });

            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
