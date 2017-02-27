package org.saai.reservation.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saai.reservation.api.core.GsonHelper;
import org.saai.reservation.api.services.OrdersService;
import org.saai.reservation.api.services.ShowsService;
import org.saai.reservation.api.services.dataobjects.ApiError;
import org.saai.reservation.api.services.dataobjects.CartTimeoutSeats;
import org.saai.reservation.api.services.dataobjects.IndividualShows;
import org.saai.reservation.api.services.dataobjects.OrdersRequest;
import org.saai.reservation.api.services.dataobjects.OrdersResponse;
import org.saai.reservation.api.services.dataobjects.ShowSeats;
import org.saai.reservation.api.services.dataobjects.ShowSeatsCarts;
import org.saai.reservation.api.services.dataobjects.Shows;
import org.saai.reservation.api.services.dataobjects.ShowsAvailable;

import com.google.gson.JsonSyntaxException;

@Path("/shows")
public class ShowsResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response showsDetails() {
		ShowsService showsService = new ShowsService();
		ApiError apiError = new ApiError();
		List<Shows> shows = showsService.getAllShows();
		if (shows != null) {
			ShowsAvailable showsAvailable = new ShowsAvailable(shows);
			return Response.status(Response.Status.OK).entity(showsAvailable).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}
	}

	@GET
	@Path("/{showId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response individualShowInformaiton(@PathParam("showId") int showId) {

		ShowsService showsService = new ShowsService();
		ApiError apiError = new ApiError();
		IndividualShows individualShows = showsService.getAllInformationOfSelectedShowId(showId);
		if (individualShows != null) {
			return Response.status(Response.Status.OK).entity(individualShows).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@GET
	@Path("/timings/{showTimingId}/seats")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response availableSeatNumbers(@PathParam("showTimingId") int showTimingId) {

		ShowsService showsService = new ShowsService();
		ApiError apiError = new ApiError();
		ShowSeats showSeats = showsService.getSeatsInfoByShowTimingId(showTimingId);
		if (showSeats != null) {
			return Response.status(Response.Status.OK).entity(showSeats).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@GET
	@Path("/timings/{showTimingId}/seats/carts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response availableSeatNumbersFromCarts(@PathParam("showTimingId") int showTimingId) {

		ShowsService showsService = new ShowsService();
		ApiError apiError = new ApiError();
		ShowSeatsCarts showSeatsCarts = showsService.getSeatsInfoInCartsByShowTimingId(showTimingId);
		if (showSeatsCarts != null) {
			return Response.status(Response.Status.OK).entity(showSeatsCarts).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@PUT
	@Path("/seats/{seatId}/carts/status/{cartStatus}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSeatSelection(@PathParam("seatId") int seatId, @PathParam("cartStatus") int cartStatus) {

		ShowsService showsService = new ShowsService();
		ApiError apiError = new ApiError();

		int updateCartStatus = showsService.updateCartSeat(seatId, cartStatus);
		switch (updateCartStatus) {
		case 200:
			return Response.status(Response.Status.OK).build();
		case 3000:
			apiError.setErrorCode(updateCartStatus);
			apiError.setErrorMessage("Invalid cart status");
			return Response.status(Response.Status.NOT_FOUND).entity(apiError).build();
		case 3001:
			apiError.setErrorCode(updateCartStatus);
			apiError.setErrorMessage("Sorry the seat is already occupied");
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(apiError).build();
		default:
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@PUT
	@Path("/seats/carts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTimeOutCartSeats(String deleteTimeOutSeatsJson) {

		ShowsService showsService = new ShowsService();
		ApiError apiError = new ApiError();
		try {

			CartTimeoutSeats cartTimeoutSeats = GsonHelper.fromJson(deleteTimeOutSeatsJson, CartTimeoutSeats.class);

			int deleteTimeOutCartStatus = showsService.deleteCartSeatsOnTimeOut(cartTimeoutSeats);
			switch (deleteTimeOutCartStatus) {
			case 200:
				return Response.status(Response.Status.OK).build();
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

}
