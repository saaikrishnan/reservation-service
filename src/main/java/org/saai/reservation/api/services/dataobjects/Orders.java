package org.saai.reservation.api.services.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	private List<OrderList> orders = new ArrayList<OrderList>();

	public Orders() {

	}

	public Orders(List<OrderList> orders) {
		super();
		this.orders = orders;
	}

	public List<OrderList> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderList> orders) {
		this.orders = orders;
	}

}
