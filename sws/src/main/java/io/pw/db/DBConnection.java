package io.pw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by pwykowski
 */
public class DBConnection {

	private static Connection connection;

	static Connection connect() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:h2:mem:test");
			}
			return connection;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void setUpDB() {
		connect();
		try {
			connection.prepareStatement("""
					CREATE TABLE product (
						id bigint auto_increment,
						name varchar(255),
						desc varchar(2500),
						serial varchar(8),
						qty int,
						PRIMARY KEY (id)
					);
					""").execute();
			int updateCount = connection.prepareStatement("""
					INSERT INTO product (name, desc, serial, qty) VALUES
						('MacBook Pro', 'With M1 Pro chip', '1234-567', 22),
						('MacBook Air', 'With M2 chip', '3123-412', 41),
						('Xbox Series X', 'Includes 2 pads and Fifa 2023', '4145-241', 12),
						('PlayStation 5', 'With a VR set', '0910-131', 98),
						('Samsung S22 Ultra', 'Has a great camera', '0011-111', 99)
					""").executeUpdate();
			System.out.printf(">>%d rows added...", updateCount);

//			Statement st = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
//			final ResultSet resultSet = st.executeQuery("SELECT * FROM product WHERE name='PlayStation 5'");
//			if (resultSet.next()) {
//				final int qty = resultSet.getInt("qty");
//				resultSet.updateInt("qty", qty + 1);
//				resultSet.updateRow();
//			}
//			ŃŃ
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}


	}

}
