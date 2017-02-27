package org.saai.reservation.api.services.dataobjects;

public class SeatsCarts {
	private int seatId;
	private int cartStatus;
	
	public SeatsCarts(){
		
	}
	
	public SeatsCarts(int seatId, int cartStatus) {
		super();
		this.seatId = seatId;
		this.cartStatus = cartStatus;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(int cartStatus) {
		this.cartStatus = cartStatus;
	}
	
	

}
