package io.pw;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Created by pwykowski
 */
public class SimpleWebServer extends AbstractVerticle {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new SimpleWebServer());
	}

	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(req -> {
			req.response()
					.putHeader("content-type", "text/plain")
					.end("Hello from Vert.x!");
		}).listen(8080);
	}

}
