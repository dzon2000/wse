package io.pw.event;

import io.vertx.core.Vertx;

/**
 * Created by pwykowski
 */
public class Application {

	public static void main(String[] args) {
		final Vertx vertx = Vertx.vertx();

		vertx.deployVerticle(new Server());
		vertx.deployVerticle(new SaveToFile());
	}

}
