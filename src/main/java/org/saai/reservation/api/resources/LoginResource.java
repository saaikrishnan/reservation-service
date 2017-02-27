package org.saai.reservation.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saai.reservation.api.core.GsonHelper;
import org.saai.reservation.api.services.LoginService;
import org.saai.reservation.api.services.dataobjects.ApiError;
import org.saai.reservation.api.services.dataobjects.Login;

import com.google.gson.JsonSyntaxException;

@Path("/sessions")
public class LoginResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(String authenticationJson) {

		LoginService loginService = new LoginService();
		ApiError apiError = new ApiError();
		try {
			Login login = GsonHelper.fromJson(authenticationJson, Login.class);
			int loginStatus = loginService.authenticate(login);
			switch (loginStatus) {

			case 200:
				return Response.status(Response.Status.OK).entity(login).build();
			case 2000:
				apiError.setErrorCode(2000);
				apiError.setErrorMessage("Enter a valid username");
				return Response.status(Response.Status.UNAUTHORIZED).entity(apiError).build();
			case 2002:
				apiError.setErrorCode(loginStatus);
				apiError.setErrorMessage("Password is incorrect");
				return Response.status(Response.Status.UNAUTHORIZED).entity(apiError).build();
			default:
				apiError.setErrorCode(9000);
				apiError.setErrorMessage("Something went wrong with the server. Please try again later");
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
			}
		}

		catch (JsonSyntaxException jEx) {
			apiError.setErrorCode(9001);
			apiError.setErrorMessage("Invalid JSON");
			return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
		}
	}
}