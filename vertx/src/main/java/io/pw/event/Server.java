package io.pw.event;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;

/**
 * Created by pwykowski
 */
public class Server extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(req -> {
			//  Handle form by reading data and sending it to file verticle
			if (req.path().equals("/persist") && req.method().equals(HttpMethod.POST)) {
				req.setExpectMultipart(true);
				req.endHandler(v -> {
							final MultiMap entries = req.formAttributes();
							final String data = entries.get("data");
							// Send message to other Verticle which will save data to a file
							vertx.eventBus().send("io.event.saveToFile", data);
						})
						.response()
						.putHeader("content-type", "text/plain")
						.end("Thanks for submitting!");
			} else {
				// Display simple HTML form
				req.response()
						.putHeader("content-type", "text/html")
						.end(
								"""
											<form action="/persist" method="POST">
												<label for="data">Data to save:</label><br>
												<textarea id="data" name="data" rows="10" cols="50"></textarea><br>
												<input type="submit">
											</form>
										"""
						);
			}
		}).listen(8080);
	}

}
