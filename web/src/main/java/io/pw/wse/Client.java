package io.pw.wse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created by pwykowski
 */
public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		HttpRequest.Builder requestBuilder = HttpRequest
				.newBuilder()
				.uri(URI.create("http://localhost:9090"));
		final HttpRequest request = requestBuilder.build();
		final HttpClient httpClient = HttpClient.newHttpClient();
		final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		if (response.statusCode() == 200) {
			System.out.println(response.body());
		} else if (response.statusCode() == 404) {
			System.out.println("Nie znaleziono zasobu...");
		}
	}

}
