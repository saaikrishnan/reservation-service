package org.saai.reservation.api.services.dataobjects;

public class Seats {
	private int seatId;
	private String rowNumber;
	private int columnNumber;
	private int bookingStatus;
	
	public Seats(){
		
	}
	
	public Seats(int seatId, String rowNumber, int columnNumber, int bookingStatus) {
		super();
		this.seatId = seatId;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		this.bookingStatus = bookingStatus;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	

}
