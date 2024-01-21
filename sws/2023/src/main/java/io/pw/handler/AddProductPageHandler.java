package io.pw.handler;

import io.pw.WarehouseApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by pwykowski
 */
public class AddProductPageHandler implements ResponseHandler {
	private static final Logger logger = LogManager.getLogger(AddProductPageHandler.class);

	@Override
	public byte[] handle() {
		logger.info("Reading the add.html page");
		try {
			String template = Files.readString(Path.of(WarehouseApplication.CONTENT_ROOT, "add.html"));
			return template.getBytes(StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
