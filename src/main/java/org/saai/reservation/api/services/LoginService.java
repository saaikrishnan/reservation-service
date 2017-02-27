package org.saai.reservation.api.services;

import org.saai.reservation.api.dao.mysql.LoginDAOImpl;
import org.saai.reservation.api.services.dataobjects.Login;

public class LoginService {

	public int authenticate(Login login) {
		LoginDAOImpl loginDAOImpl = new LoginDAOImpl();
		int status = loginDAOImpl.validateCredentials(login);
		return status;
	}

}
