package org.saai.reservation.api.services.dataobjects;

import java.util.List;

public class Screens {
	private int screenId;
	private String screenName;
	private List<ShowTimings> showTimings;

	public Screens(int screenId, String screenName, List<ShowTimings> showTimings) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
		this.showTimings = showTimings;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public List<ShowTimings> getShowTimings() {
		return showTimings;
	}

	public void setShowTimings(List<ShowTimings> showTimings) {
		this.showTimings = showTimings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + screenId;
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		result = prime * result + ((showTimings == null) ? 0 : showTimings.hashCode());
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
		Screens other = (Screens) obj;
		if (screenId != other.screenId)
			return false;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		if (showTimings == null) {
			if (other.showTimings != null)
				return false;
		} else if (!showTimings.equals(other.showTimings))
			return false;
		return true;
	}
	
	

}
