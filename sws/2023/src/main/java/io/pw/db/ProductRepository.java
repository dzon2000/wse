package io.pw.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by pwykowski
 */
public class ProductRepository implements Repository<Product> {

	private final Connection conn = DBConnection.connect();

	@Override
	public List<Product> findAll() {
		try {
			List<Product> result = new ArrayList<>();
			final ResultSet resultSet = conn.prepareStatement("SELECT id, name, desc, serial, qty FROM product").executeQuery();
			while (resultSet.next()) {
				final long id = resultSet.getLong("id");
				final String name = resultSet.getString("name");
				final String desc = resultSet.getString("desc");
				final String serial = resultSet.getString("serial");
				final int qty = resultSet.getInt("qty");
				result.add(new Product(
						id, name, desc, serial, qty
				));
			}
			return Collections.unmodifiableList(result);
		} catch (SQLException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Optional<Product> findById(long id) {
		try {
			final PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, name, desc, serial, qty FROM product WHERE id = ?");
			preparedStatement.setLong(1, id);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				final long dbId = resultSet.getLong("id");
				final String name = resultSet.getString("name");
				final String desc = resultSet.getString("desc");
				final String serial = resultSet.getString("serial");
				final int qty = resultSet.getInt("qty");
				return Optional.of(new Product(
						dbId, name, desc, serial, qty
				));
			}
		} catch (SQLException e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public void store(Product product) {
		try {
			final PreparedStatement preparedStatement = conn.prepareStatement("""
					INSERT INTO product (name, desc, serial, qty) VALUES (?, ?, ?, ?)
					""");
			preparedStatement.setString(1, product.name());
			preparedStatement.setString(2, product.desc());
			preparedStatement.setString(3, product.serialNumber());
			preparedStatement.setInt(4, product.quantity());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void update(Product product) {
		try {
			final PreparedStatement preparedStatement = conn.prepareStatement("""
					UPDATE product SET name = ?, desc = ?, serial = ?, qty = ? WHERE id = ?
					""");
			preparedStatement.setString(1, product.name());
			preparedStatement.setString(2, product.desc());
			preparedStatement.setString(3, product.serialNumber());
			preparedStatement.setInt(4, product.quantity());
			preparedStatement.setLong(5, product.id());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
