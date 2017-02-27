package org.saai.reservation.api.services.dataobjects;

public class OrderList {

	private String fullName;
	private String emailId;
	private String showName;
	private String date;
	private String time;
	private String screenName;
	private int numberOfSeatsBooked;
	private String seatNumbers;

	public OrderList() {

	}

	public OrderList(String fullName, String emailId, String showName, String date, String time, String screenName,
			int numberOfSeatsBooked, String seatNumbers) {
		super();
		this.fullName = fullName;
		this.emailId = emailId;
		this.showName = showName;
		this.date = date;
		this.time = time;
		this.screenName = screenName;
		this.numberOfSeatsBooked = numberOfSeatsBooked;
		this.seatNumbers = seatNumbers;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getNumberOfSeatsBooked() {
		return numberOfSeatsBooked;
	}

	public void setNumberOfSeatsBooked(int numberOfSeatsBooked) {
		this.numberOfSeatsBooked = numberOfSeatsBooked;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

}
