package io.pw.response;

import io.pw.db.ProductRepository;
import io.pw.db.Repository;
import io.pw.db.entity.Product;
import io.pw.util.QueryParamParser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class StoreNewProductHandler implements ResponseHandler {

    private final InputStream requestBody;
    private final Repository<Product> repository = new ProductRepository();

    public StoreNewProductHandler(InputStream requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public byte[] handle() {
        try {
            String body = URLDecoder.decode(new String(requestBody.readAllBytes()), StandardCharsets.UTF_8);
            JSONObject newProduct = QueryParamParser.parseQueryString(body);
            Product product = new Product(
                    newProduct.getString("name"),
                    newProduct.getString("desc"),
                    newProduct.getString("serial"),
                    newProduct.getInt("qty")
            );
            repository.store(product);
            return new byte[0];
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
