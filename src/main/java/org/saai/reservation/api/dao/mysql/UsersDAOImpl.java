package org.saai.reservation.api.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.saai.reservation.api.services.dataobjects.UserInfoResponse;
import org.saai.reservation.api.services.dataobjects.Users;

public class UsersDAOImpl {

	Database db = new Database();
	Connection connection = null;
	PreparedStatement preparedStatementUserDB = null;
	PreparedStatement preparedStatementLoginDB = null;

	public boolean isUserAlreadyExists(String emailId) {
		try {
			connection = db.getConnection();
			String query = "SELECT emailId FROM UsersDB1 WHERE emailId like '" + emailId + "'";
			preparedStatementLoginDB = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatementLoginDB.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("emailId").equalsIgnoreCase(emailId)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public UserInfoResponse getUserInformationFromEmailId(String emailId) {
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		try {
			connection = db.getConnection();
			String query = "SELECT * FROM UsersDB1 WHERE emailId like '" + emailId + "'";
			preparedStatementLoginDB = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatementLoginDB.executeQuery();
			while (resultSet.next()) {
				userInfoResponse.setUuid(resultSet.getString("UUID"));
				userInfoResponse.setEmailId(resultSet.getString("emailId"));
				userInfoResponse.setFirstName(resultSet.getString("FirstName"));
				userInfoResponse.setLastName(resultSet.getString("LastName"));
				return userInfoResponse;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean createUser(Users user) {
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			String query = "INSERT INTO UsersDB1 (UUID,FirstName,LastName,emailId) VALUES (?,?,?,?)";
			preparedStatementUserDB = connection.prepareStatement(query);
			preparedStatementUserDB.setString(1, user.getUuid());
			preparedStatementUserDB.setString(2, user.getFirstName());
			preparedStatementUserDB.setString(3, user.getLastName());
			preparedStatementUserDB.setString(4, user.getEmailId());
			preparedStatementUserDB.execute();
			query = "INSERT INTO LoginDB1 (emailId,password) VALUES (?,?)";
			preparedStatementLoginDB = connection.prepareStatement(query);
			preparedStatementLoginDB.setString(1, user.getEmailId());
			preparedStatementLoginDB.setString(2, user.getPassword());
			preparedStatementLoginDB.execute();
			connection.commit();
			connection.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
