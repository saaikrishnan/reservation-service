package org.saai.reservation.api.services.dataobjects;

import java.util.List;

public class ShowsAvailable {

	private List<Shows> shows;

	public ShowsAvailable(){
		
	}
	
	public ShowsAvailable(List<Shows> shows) {
		super();
		this.shows = shows;
	}

	public List<Shows> getShows() {
		return shows;
	}

	public void setShows(List<Shows> shows) {
		this.shows = shows;
	}
	
	
	
}
