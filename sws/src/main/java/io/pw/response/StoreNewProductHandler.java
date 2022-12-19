package io.pw.response;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static io.pw.WebServer.PRODUCTS;

public class StoreNewProductHandler implements ResponseHandler {

    private InputStream requestBody;
    public StoreNewProductHandler(InputStream requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public byte[] handle() {
        try {
            String body = URLDecoder.decode(new String(requestBody.readAllBytes()), StandardCharsets.UTF_8);
            String[] params = body.split("&");
            JSONObject newProduct = new JSONObject();
            for (String param : params) {
                String[] paramAndValue = param.split("=");
                newProduct.put(paramAndValue[0], paramAndValue[1]);
            }
            PRODUCTS.put(newProduct);
            return new byte[0];
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
