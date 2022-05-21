package io.pw.event;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pwykowski
 */
public class SaveToFile extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("io.event.saveToFile", handler -> {
			String message = (String) handler.body();
			String data = getBytes(message);
			vertx.fileSystem().writeFile("secret.txt", Buffer.buffer(data));
		});
	}

	private String getBytes(String message) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		final byte[] digest = md.digest(message.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
