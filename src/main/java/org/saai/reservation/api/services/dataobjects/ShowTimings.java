package org.saai.reservation.api.services.dataobjects;

public class ShowTimings {
	private int showTimingId;
	private String showTime;
	private int ticketsAvailable;

	public ShowTimings(int showTimingId, String showTime, int ticketsAvailable) {
		this.showTimingId = showTimingId;
		this.showTime = showTime;
		this.ticketsAvailable = ticketsAvailable;
	}

	public ShowTimings() {
		// TODO Auto-generated constructor stub
	}

	public int getShowTimingId() {
		return showTimingId;
	}

	public void setShowTimingId(int showTimingId) {
		this.showTimingId = showTimingId;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public int getTicketsAvailable() {
		return ticketsAvailable;
	}

	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((showTime == null) ? 0 : showTime.hashCode());
		result = prime * result + showTimingId;
		result = prime * result + ticketsAvailable;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowTimings other = (ShowTimings) obj;
		if (showTime == null) {
			if (other.showTime != null)
				return false;
		} else if (!showTime.equals(other.showTime))
			return false;
		if (showTimingId != other.showTimingId)
			return false;
		if (ticketsAvailable != other.ticketsAvailable)
			return false;
		return true;
	}

}
