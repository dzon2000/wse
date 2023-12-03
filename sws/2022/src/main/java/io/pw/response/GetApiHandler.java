package io.pw.response;

import io.pw.db.ProductRepository;
import io.pw.db.Repository;
import io.pw.db.entity.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class GetApiHandler implements ResponseHandler {

    private final Repository<Product> repository = new ProductRepository();

    @Override
    public byte[] handle() {
        final JSONArray productsArray = getProducts();
        String response = new JSONObject().put("products", productsArray).toString();
        return response.getBytes();
    }

    private JSONArray getProducts() {
        final List<Product> products = repository.findAll();
        final JSONArray productsArray = new JSONArray();
        products.forEach(p -> {
            productsArray.put(p.transform());
        });
        return productsArray;
    }
}
