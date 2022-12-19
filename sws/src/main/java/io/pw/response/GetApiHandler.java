package io.pw.response;

import org.json.JSONObject;

import static io.pw.WebServer.PRODUCTS;

public class GetApiHandler implements ResponseHandler {

    @Override
    public byte[] handle() {
        String response = new JSONObject().put("products", PRODUCTS).toString();
        return response.getBytes();
    }
}
