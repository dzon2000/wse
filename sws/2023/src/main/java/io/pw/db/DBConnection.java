package io.pw.db;

import java.sql.Connection;
import java.sql.DriverManager;
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
				connection = DriverManager.getConnection("jdbc:h2:mem:warehouse");
			}
			return connection;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void initDB() {
		Connection dbConnection = connect();
		try {
			final Statement statement = dbConnection.createStatement();
			statement.execute("""
					CREATE TABLE product (
						id bigint auto_increment,
						name varchar(255),
						desc varchar(2500),
						serial varchar(8),
						qty int,
						PRIMARY KEY (id)
					);
					""");

			final Statement insert = connection.createStatement();
			insert.execute("""
					INSERT INTO product (name, desc, serial, qty) VALUES
											('MacBook Pro', 'With M1 Pro chip', '1234-567', 22),
											('MacBook Air', 'With M2 chip', '3123-412', 41),
											('Xbox Series X', 'Includes 2 pads and Fifa 2023', '4145-241', 12),
											('PlayStation 5', 'With a VR set', '0910-131', 98),
											('Samsung S22 Ultra', 'Has a great camera', '0011-111', 99)
					""");
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
