package org.saai.reservation.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saai.reservation.api.core.GsonHelper;
import org.saai.reservation.api.services.OrdersService;
import org.saai.reservation.api.services.dataobjects.ApiError;
import org.saai.reservation.api.services.dataobjects.OrderSummary;
import org.saai.reservation.api.services.dataobjects.OrderUpdate;
import org.saai.reservation.api.services.dataobjects.Orders;
import org.saai.reservation.api.services.dataobjects.OrdersRequest;
import org.saai.reservation.api.services.dataobjects.OrdersResponse;

import com.google.gson.JsonSyntaxException;

@Path("/orders")
public class OrdersResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOrder(String orderJson, @QueryParam("seats") int seats) {

		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();
		try {
			OrdersRequest order = GsonHelper.fromJson(orderJson, OrdersRequest.class);

			int orderId = ordersService.createNewOrder(order, seats);
			OrdersResponse ordersResponse = new OrdersResponse(orderId);
			if (orderId != -1) {
				return Response.status(Response.Status.CREATED).entity(ordersResponse).build();
			} else {
				apiError.setErrorCode(9000);
				apiError.setErrorMessage("Unable to process the order. Please try again later");
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
			}

		} catch (JsonSyntaxException jEx) {
			apiError.setErrorCode(9001);
			apiError.setErrorMessage("Invalid JSON");
			return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
		}

	}

	@PUT
	@Path("/{orderId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reselectSeats(String updateOrderJson, @PathParam("orderId") int orderId) {

		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();
		try {
			OrderUpdate orderUpdate = GsonHelper.fromJson(updateOrderJson, OrderUpdate.class);

			int updateOrderStatus = ordersService.updateOrder(orderUpdate, orderId);
			switch (updateOrderStatus) {
			case 200:
				return Response.status(Response.Status.OK).build();
			case 4001:
				apiError.setErrorCode(updateOrderStatus);
				apiError.setErrorMessage("Seats already booked, pick another one");
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(apiError).build();
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
	@Path("/{orderId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response orderSummary(@PathParam("orderId") int orderId) {
		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();

		OrderSummary orderSummary = ordersService.getOrderSummaryByOrderId(orderId);
		if (orderSummary != null) {
			return Response.status(Response.Status.OK).entity(orderSummary).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@POST
	@Path("/{orderId}/confirmations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response orderConfirmation(@PathParam("orderId") int orderId) {
		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();
		int orderStatus = ordersService.confirmOrder(orderId);
		switch (orderStatus) {
		case 200:
			return Response.status(Response.Status.OK).build();
		case 4000:
			apiError.setErrorCode(orderStatus);
			apiError.setErrorMessage("Order not available");
			return Response.status(Response.Status.NOT_FOUND).entity(apiError).build();
		default:
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@DELETE
	@Path("/{orderId}/cancellations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response orderCancellation(@PathParam("orderId") int orderId) {
		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();
		int orderStatus = ordersService.cancelOrder(orderId);
		switch (orderStatus) {
		case 200:
			return Response.status(Response.Status.OK).build();
		case 4000:
			apiError.setErrorCode(orderStatus);
			apiError.setErrorMessage("Order not available");
			return Response.status(Response.Status.NOT_FOUND).entity(apiError).build();
		default:
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

	@DELETE
	@Path("/{orderId}/carts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reselectSeats(@PathParam("orderId") int orderId) {

		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();

		int deleteCartStatus = ordersService.deleteCartSeats(orderId);
		switch (deleteCartStatus) {
		case 200:
			return Response.status(Response.Status.OK).build();
		default:
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();

		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response orderList(@QueryParam("emailId") String emailId) {
		OrdersService ordersService = new OrdersService();
		ApiError apiError = new ApiError();

		Orders orders = ordersService.getCompleteOrderDetails(emailId);
		if (orders != null) {
			return Response.status(Response.Status.OK).entity(orders).build();
		} else {
			apiError.setErrorCode(9000);
			apiError.setErrorMessage("Something went wrong with the server. Please try again later!");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError).build();
		}

	}

}
