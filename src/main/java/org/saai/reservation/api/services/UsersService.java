package org.saai.reservation.api.services;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.saai.reservation.api.dao.mysql.UsersDAOImpl;
import org.saai.reservation.api.services.dataobjects.UserInfoResponse;
import org.saai.reservation.api.services.dataobjects.Users;

public class UsersService {

	public int addNewUser(Users user) {
		UsersDAOImpl usersDAOImpl = new UsersDAOImpl();
		int missingField = checkNullOrEmptyField(user);
		if (missingField == 0) {
			if (usersDAOImpl.isUserAlreadyExists(user.getEmailId())) {
				return 2001;
			} else {
				user.setUuid(UUID.randomUUID().toString());
				if (usersDAOImpl.createUser(user)) {
					return 201;
				} else {

					return 9000;
				}
			}

		} else
			return missingField;
	}

	public int checkNullOrEmptyField(Users user) {
		if (StringUtils.isBlank(user.getEmailId())) {
			return 5000;
		} else if (StringUtils.isBlank(user.getPassword())) {
			return 5001;
		} else if (StringUtils.isBlank(user.getFirstName())) {
			return 5002;
		} else if (StringUtils.isBlank(user.getLastName())) {
			return 5003;
		} else
			return 0;

	}

	public UserInfoResponse getUserInfoByEmailId(String emailId) {
		UsersDAOImpl usersDAOImpl = new UsersDAOImpl();
		UserInfoResponse userInfoResponse = usersDAOImpl.getUserInformationFromEmailId(emailId);
		return userInfoResponse;
	}

}
