package io.pw.response;

import io.pw.db.ProductRepository;
import io.pw.db.Repository;
import io.pw.db.entity.Product;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class PostApiHandler implements ResponseHandler {

    private final InputStream requestBody;
    private final Repository<Product> repository = new ProductRepository();

    public PostApiHandler(InputStream requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public byte[] handle() {
        try (requestBody){
            String requestProduct = new String(requestBody.readAllBytes());
            final JSONObject productJSON = new JSONObject(requestProduct);
            Product product = new Product(
                    productJSON.getString("name"),
                    productJSON.getString("desc"),
                    productJSON.getString("serial"),
                    productJSON.getInt("qty")
            );
            repository.store(product);
            return "OK".getBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
