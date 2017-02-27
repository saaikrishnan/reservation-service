package org.saai.reservation.api.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public Connection getConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/testsystem";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "root");
			return connection;
		} catch (Exception e) {
			throw e;
		}

	}

}