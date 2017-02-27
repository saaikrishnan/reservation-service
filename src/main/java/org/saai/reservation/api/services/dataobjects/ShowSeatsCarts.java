package org.saai.reservation.api.services.dataobjects;

import java.util.List;

public class ShowSeatsCarts {
	
	private int showTimingId;
	private List<SeatsCarts> seats;
	
	public ShowSeatsCarts(){
		
	}

	public ShowSeatsCarts(int showTimingId, List<SeatsCarts> seats) {
		super();
		this.showTimingId = showTimingId;
		this.seats = seats;
	}

	public int getShowTimingId() {
		return showTimingId;
	}

	public void setShowTimingId(int showTimingId) {
		this.showTimingId = showTimingId;
	}

	public List<SeatsCarts> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatsCarts> seats) {
		this.seats = seats;
	}
	
	

}
