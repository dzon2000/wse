package io.pw.db;

import io.pw.db.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by pwykowski
 */
public class ProductRepository implements Repository<Product> {

	private static final Logger logger = LogManager.getLogger(ProductRepository.class);

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
			statement.setString(1, product.name());
			statement.setString(2, product.desc());
			statement.setString(3, product.serial());
			statement.setInt(4, product.qty());
			statement.executeUpdate();
		} catch (SQLException ex) {
			logger.error("Unable to store new product: {}, {}, {}, {}. Error message: {}", product.name(), product.desc(), product.serial(), product.qty(), ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void update(Product product) {
		try (final PreparedStatement statement = conn.prepareStatement("UPDATE product SET name = ?, desc = ?, serial = ?, qty = ? WHERE id = ?")) {
			statement.setString(1, product.name());
			statement.setString(2, product.desc());
			statement.setString(3, product.serial());
			statement.setInt(4, product.qty());
			statement.setLong(5, product.id());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
