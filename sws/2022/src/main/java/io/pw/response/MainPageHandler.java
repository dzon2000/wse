package io.pw.response;

import io.pw.WebServer;
import io.pw.db.ProductRepository;
import io.pw.db.Repository;
import io.pw.db.entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MainPageHandler implements ResponseHandler {

    private final Repository<Product> repository = new ProductRepository();

    private static final String ROW_TEMPLATE = """
                <tr>
                    <td>$no</td>
                    <td>$name</td>
                    <td>$desc</td>
                    <td>$serial</td>
                    <td id="qty-$id">$qty</td>
                    <td>
                        <button type="submit" onclick="reduce($id)" class="btn btn-primary">Reduce</button>
                    </td>
                </tr>
            """;
    @Override
    public byte[] handle() {
        try {
            String template = Files.readString(Path.of(WebServer.CONTENT_ROOT, "index.html"));
            String products = translateToHTML();
            return template.replace("<productData />", products)
                    .replace("<message />", getMessage()).getBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private CharSequence getMessage() {
        if (WebServer.message.isEmpty()) {
            return "";
        }
        return String.format("""
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>%s</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                """, WebServer.message);
    }

    private String translateToHTML() {
        StringBuilder products = new StringBuilder();
        final List<Product> allProducts = repository.findAll();
        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            String row = ROW_TEMPLATE.replace("$no", String.valueOf(i + 1))
                    .replace("$name", product.name())
                    .replace("$desc", product.desc())
                    .replace("$serial", product.serial())
                    .replace("$qty", String.valueOf(product.qty()))
                    .replace("$id", String.valueOf(product.id()));
            products.append(row);
        }
        return products.toString();
    }
}
