package io.pw.response;

import io.pw.WebServer;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainPageHandler implements ResponseHandler {

    private static String ROW_TEMPLATE = """
                <tr>
                    <td>$no</td>
                    <td>$name</td>
                    <td>$desc</td>
                    <td>$serial</td>
                    <td>$qty</td>
                </tr>
            """;
    @Override
    public byte[] handle() {
        try {
            String template = Files.readString(Path.of(WebServer.CONTENT_ROOT, "index.html"));
            String products = translateToHTML();
            return template.replace("<productData />", products).getBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String translateToHTML() {
        StringBuilder products = new StringBuilder();
        for (int i = 0; i < WebServer.PRODUCTS.length(); i++) {
            JSONObject product = WebServer.PRODUCTS.getJSONObject(i);
            String row = ROW_TEMPLATE.replace("$no", String.valueOf(i + 1))
                    .replace("$name", product.getString("name"))
                    .replace("$desc", product.getString("desc"))
                    .replace("$serial", product.getString("serial"))
                    .replace("$qty", String.valueOf(product.getInt("qty")));
            products.append(row);
        }
        return products.toString();
    }
}
