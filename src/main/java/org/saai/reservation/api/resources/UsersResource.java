package org.saai.reservation.api.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saai.reservation.api.core.GsonHelper;
import org.saai.reservation.api.services.UsersService;
import org.saai.reservation.api.services.dataobjects.ApiError;
import org.saai.reservation.api.services.dataobjects.UserInfoResponse;
import org.saai.reservation.api.services.dataobjects.Users;

import com.google.gson.JsonSyntaxException;

@Path("/users")
public class UsersResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(String userJson) {

		UsersService usersService = new UsersService();
		ApiError apiError = new ApiError();
		try {
			Users user = GsonHelper.fromJson(userJson, Users.class);
			int userAddStatus = usersService.addNewUser(user);
			switch (userAddStatus) {

			case 201:
				return Response.status(Response.Status.CREATED).entity(user).build();
			case 2001:
				apiError.setErrorCode(userAddStatus);
				apiError.setErrorMessage("User already exists, sign up with a different Email ID");
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(apiError).build();
			case 5000:
				apiError.setErrorCode(userAddStatus);
				apiError.setErrorMessage("Email ID cannot be empty");
				return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
			case 5001:
				apiError.setErrorCode(userAddStatus);
				apiError.setErrorMessage("Password cannot be empty");
				return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
			case 5002:
				apiError.setErrorCode(userAddStatus);
				apiError.setErrorMessage("First Name cannot be empty");
				return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
			case 5003:
				apiError.setErrorCode(userAddStatus);
				apiError.setErrorMessage("Last Name cannot be empty");
				return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
			default:
				apiError.setErrorCode(9000);
				apiError.setErrorMessage("Something went wrong with the server. Please try again later");
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
			}
		} catch (JsonSyntaxException jEx) {
			apiError.setErrorCode(9001);
			apiError.setErrorMessage("Invalid JSON");
			return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
		}
	}

	@GET
	@Path("/{emailId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewUserDetails(@PathParam("emailId") String emailId) {

		UsersService usersService = new UsersService();
		ApiError apiError = new ApiError();
		if (emailId != null)
			try {
				emailId = URLDecoder.decode(emailId, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		UserInfoResponse userInfoResponse = usersService.getUserInfoByEmailId(emailId);
		if (userInfoResponse != null) {
			return Response.status(Response.Status.OK).entity(userInfoResponse).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

}
