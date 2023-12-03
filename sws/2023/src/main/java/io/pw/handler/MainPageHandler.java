package io.pw.handler;

import io.pw.WarehouseApplication;
import io.pw.db.ProductRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by pwykowski
 */
public class MainPageHandler implements ResponseHandler {

	private static final String ROW_TEMPLATE = """
                <tr>
                    <td>$id</td>
                    <td>$name</td>
                    <td>$desc</td>
                    <td>$serial</td>
                    <td>$qty</td>
                </tr>
            """;

	@Override
	public byte[] handle() {
		try {
			String template = Files.readString(Path.of(WarehouseApplication.CONTENT_ROOT, "index.html"));

			StringBuilder data = new StringBuilder("""
     			<table border=1>
     				<tr>
     					<th>Id</th>
     					<th>Name</th>
     					<th>Description</th>
     					<th>Serial Number</th>
     					<th>Quantity</th>
     				</tr>
					""");
			ProductRepository.PRODUCTS.forEach((product -> {
				data.append(
						ROW_TEMPLATE.replace("$id", String.valueOf(product.id()))
								.replace("$name", product.name())
								.replace("$desc", product.desc())
								.replace("$serial", product.serialNumber())
								.replace("$qty", String.valueOf(product.quantity()))
				);
			}));
			data.append("</table>");

			String response = template.replace("<productData />", data.toString());

			return response.getBytes(StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}