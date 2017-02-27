package org.saai.reservation.api.services.dataobjects;

public class OrderSummary {
	private String firstName;
	private String lastName;
	private String showName;
	private String showDate;
	private String showTime;
	private String screenName;
	private int numberOfSeatsBooked;
	private String[] seatNumbers;

	public OrderSummary() {

	}

	public OrderSummary(String firstName, String lastName, String showName, String showDate, String showTime,
			String screenName, int numberOfSeatsBooked, String[] seatNumbers) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.showName = showName;
		this.showDate = showDate;
		this.showTime = showTime;
		this.screenName = screenName;
		this.numberOfSeatsBooked = numberOfSeatsBooked;
		this.seatNumbers = seatNumbers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
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

	public String[] getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String[] seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

}
