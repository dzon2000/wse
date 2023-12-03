package io.pw.response;

import io.pw.WebServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AddProductFormHandler implements ResponseHandler {
    @Override
    public byte[] handle() {
        try {
            String response = Files.readString(Path.of(WebServer.CONTENT_ROOT, "addProduct.html"));
            return response.getBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
