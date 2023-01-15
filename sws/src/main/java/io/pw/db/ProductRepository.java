package io.pw.db;

import io.pw.db.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by pwykowski
 */
public class ProductRepository implements Repository<Product> {

	private final Connection conn = DBConnection.connect();

	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();
		try {
			final ResultSet resultSet = conn.prepareStatement("SELECT * FROM product").executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				String desc = resultSet.getString("desc");
				String serial = resultSet.getString("serial");
				int qty = resultSet.getInt("qty");
				products.add(new Product(id, name, desc, serial, qty));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return products;
	}

	@Override
	public Optional<Product> findById(Long id) {
		try {
			final PreparedStatement statement = conn.prepareStatement("SELECT name, desc, serial, qty FROM product WHERE id = ?");
			statement.setLong(1, id);
			final ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String desc = resultSet.getString("desc");
				String serial = resultSet.getString("serial");
				int qty = resultSet.getInt("qty");
				return Optional.of(new Product(id, name, desc, serial, qty));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return Optional.empty();
	}

	@Override
	public void store(Product product) {
		try (final PreparedStatement statement = conn.prepareStatement("INSERT INTO product (name, desc, serial, qty) VALUES (?, ?, ?, ?)")) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getDesc());
			statement.setString(3, product.getSerial());
			statement.setInt(4, product.getQty());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void update(Product product) {
		try (final PreparedStatement statement = conn.prepareStatement("UPDATE product SET name = ?, desc = ?, serial = ?, qty = ? WHERE id = ?")) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getDesc());
			statement.setString(3, product.getSerial());
			statement.setInt(4, product.getQty());
			statement.setLong(5, product.getId());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
