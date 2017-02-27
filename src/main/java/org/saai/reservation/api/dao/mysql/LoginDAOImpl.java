package org.saai.reservation.api.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.saai.reservation.api.services.dataobjects.Login;

public class LoginDAOImpl {

	public int validateCredentials(Login login) {
		try {
			Database db = new Database();
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM LoginDB1 WHERE EmailID =\"" + login.getEmailId() + "\"");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("EmailID").equalsIgnoreCase(login.getEmailId().trim()) && resultSet.getString("password").equals(login.getPassword())) {
						return 200;
					} else
						return 2002;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return 9000;
		}
		return 2000;
	}

}
