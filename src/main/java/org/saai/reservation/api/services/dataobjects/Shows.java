package org.saai.reservation.api.services.dataobjects;

public class Shows {
	private int showId;
	private String showName;
	private String showDescription;

	public Shows() {

	}

	public Shows(int showId, String showName, String showDescription) {
		super();
		this.showId = showId;
		this.showName = showName;
		this.showDescription = showDescription;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowDescription() {
		return showDescription;
	}

	public void setShowDescription(String showDescription) {
		this.showDescription = showDescription;
	}

}
