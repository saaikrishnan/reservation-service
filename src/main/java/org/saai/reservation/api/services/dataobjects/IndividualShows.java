package org.saai.reservation.api.services.dataobjects;

import java.util.List;

public class IndividualShows {
	private int showId;
	private String showName;
	private String showDescription;
	private List<ShowDetails> showDetails;

	public IndividualShows(int showId, String showName, String showDescription, List<ShowDetails> showDetails) {
		super();
		this.showId = showId;
		this.showName = showName;
		this.showDescription = showDescription;
		this.showDetails = showDetails;
	}

	public IndividualShows() {
		// TODO Auto-generated constructor stub
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

	public List<ShowDetails> getShowDetails() {
		return showDetails;
	}

	public void setShowDetails(List<ShowDetails> showDetails) {
		this.showDetails = showDetails;
	}

}
