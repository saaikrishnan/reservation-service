package org.saai.reservation.api.services.dataobjects;

public class CartTimeoutSeats {

	private int[] seatIds;

	public CartTimeoutSeats() {

	}

	public CartTimeoutSeats(int[] seatIds) {
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
