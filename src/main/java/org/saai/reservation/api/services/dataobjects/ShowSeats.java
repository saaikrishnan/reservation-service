package org.saai.reservation.api.services.dataobjects;

import java.util.List;

public class ShowSeats {
	
	private int showTimingId;
	private List<Seats> seats;
	
	public ShowSeats(){
		
	}

	public ShowSeats(int showTimingId, List<Seats> seats) {
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

	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
	
	

}
