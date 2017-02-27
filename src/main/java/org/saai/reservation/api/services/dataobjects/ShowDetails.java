package org.saai.reservation.api.services.dataobjects;

import java.util.List;

public class ShowDetails {

	private String showDate;
	private List<Screens> screens;

	public ShowDetails(String showDate, List<Screens> screens) {
		super();
		this.showDate = showDate;
		this.screens = screens;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public List<Screens> getScreens() {
		return screens;
	}

	public void setScreens(List<Screens> screens) {
		this.screens = screens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((screens == null) ? 0 : screens.hashCode());
		result = prime * result + ((showDate == null) ? 0 : showDate.hashCode());
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
		ShowDetails other = (ShowDetails) obj;
		if (screens == null) {
			if (other.screens != null)
				return false;
		} else if (!screens.equals(other.screens))
			return false;
		if (showDate == null) {
			if (other.showDate != null)
				return false;
		} else if (!showDate.equals(other.showDate))
			return false;
		return true;
	}
	
	

}
