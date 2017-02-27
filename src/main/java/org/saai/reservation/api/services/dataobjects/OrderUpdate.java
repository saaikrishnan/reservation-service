package org.saai.reservation.api.services.dataobjects;

public class OrderUpdate {

	private int[] seatIds;

	public OrderUpdate() {

	}

	public OrderUpdate(int[] seatIds) {
		super();
		this.seatIds = seatIds;
	}

	public int[] getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(int[] seatIds) {
		this.seatIds = seatIds;
	}

}
