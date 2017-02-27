package org.saai.reservation.api.services;

import org.saai.reservation.api.dao.mysql.OrdersDAOImpl;
import org.saai.reservation.api.services.dataobjects.OrderSummary;
import org.saai.reservation.api.services.dataobjects.OrderUpdate;
import org.saai.reservation.api.services.dataobjects.Orders;
import org.saai.reservation.api.services.dataobjects.OrdersRequest;

public class OrdersService {

	public int createNewOrder(OrdersRequest order, int numberOfSeats) {

		if (numberOfSeats == order.getSeatIds().length) {
			OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
			if (ordersDAOImpl.validateIfSeatsEmpty(order)) {
				int orderId = ordersDAOImpl.addNewOrder(order, numberOfSeats);
				return orderId;
			} else
				return -1;
		} else
			return -1;
	}

	public int updateOrder(OrderUpdate orderUpdate, int orderId) {

		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();

		if (ordersDAOImpl.validateIfSeatsEmptyToReOrder(orderUpdate, orderId)) {
			int orderUpdateStatus = ordersDAOImpl.updateExistingOrder(orderUpdate, orderId);
			return orderUpdateStatus;
		} else
			return 4001;

	}

	public OrderSummary getOrderSummaryByOrderId(int orderId) {
		OrderSummary orderSummary = null;
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		if (ordersDAOImpl.validateIfOrderExists(orderId)) {
			orderSummary = ordersDAOImpl.getOrderSummary(orderId);
		}
		return orderSummary;
	}

	public int confirmOrder(int orderId) {
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		if (ordersDAOImpl.validateIfOrderExists(orderId)) {
			int orderStatus = ordersDAOImpl.confirmOrderStatus(orderId);
			return orderStatus;
		} else
			return 4000;
	}

	public int cancelOrder(int orderId) {
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		if (ordersDAOImpl.validateIfOrderExists(orderId)) {
			int orderStatus = ordersDAOImpl.cancelOrderStatus(orderId);
			return orderStatus;
		} else
			return 4000;
	}

	public int deleteCartSeats(int orderId) {
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		int cartDeleteStatus = ordersDAOImpl.removeCancelledSeatsFromCart(orderId);
		return cartDeleteStatus;
	}

	public Orders getCompleteOrderDetails(String emailId) {
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		Orders order = ordersDAOImpl.ordersCompleteList(emailId);
		return order;
	}

}
