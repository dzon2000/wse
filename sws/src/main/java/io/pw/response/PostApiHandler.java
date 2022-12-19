package io.pw.response;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static io.pw.WebServer.PRODUCTS;

public class PostApiHandler implements ResponseHandler {

    private InputStream requestBody;

    public PostApiHandler(InputStream requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public byte[] handle() {
        try {
            String requestProduct = new String(requestBody.readAllBytes());
            PRODUCTS.put(new JSONObject(requestProduct));
            return "OK".getBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                requestBody.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
