package org.saai.reservation.api.services;

import java.util.List;

import org.saai.reservation.api.dao.mysql.ShowsDAOImpl;
import org.saai.reservation.api.services.dataobjects.CartTimeoutSeats;
import org.saai.reservation.api.services.dataobjects.IndividualShows;
import org.saai.reservation.api.services.dataobjects.ShowSeats;
import org.saai.reservation.api.services.dataobjects.ShowSeatsCarts;
import org.saai.reservation.api.services.dataobjects.Shows;

public class ShowsService {

	public List<Shows> getAllShows() {
		ShowsDAOImpl showsDAOImpl = new ShowsDAOImpl();
		List<Shows> shows = showsDAOImpl.getAllShowDetails();
		return shows;

	}

	public IndividualShows getAllInformationOfSelectedShowId(int showId) {
		ShowsDAOImpl showsDAOImpl = new ShowsDAOImpl();
		IndividualShows individualShows = showsDAOImpl.getAllDetailsOfShow(showId);
		return individualShows;
	}

	public ShowSeats getSeatsInfoByShowTimingId(int showTimingId) {
		ShowSeats showSeats = new ShowSeats();
		ShowsDAOImpl showsDAOImpl = new ShowsDAOImpl();
		showSeats = showsDAOImpl.getShowSeatsByPTID(showTimingId);
		return showSeats;

	}

	public ShowSeatsCarts getSeatsInfoInCartsByShowTimingId(int showTimingId) {
		ShowSeatsCarts showSeatsCarts = new ShowSeatsCarts();
		ShowsDAOImpl showsDAOImpl = new ShowsDAOImpl();
		showSeatsCarts = showsDAOImpl.getShowSeatsInCartsByPTID(showTimingId);
		return showSeatsCarts;

	}

	public int updateCartSeat(int seatId, int cartStatus) {
		ShowsDAOImpl showsDAOImpl = new ShowsDAOImpl();
		int cartUpdateStatus = showsDAOImpl.updateCartSeatSelectionStatus(seatId, cartStatus);
		return cartUpdateStatus;

	}

	public int deleteCartSeatsOnTimeOut(CartTimeoutSeats cartTimeoutSeats) {
		ShowsDAOImpl showsDAOImpl = new ShowsDAOImpl();
		int deleteCartSeatsStatus = showsDAOImpl.removeTimeOutSeats(cartTimeoutSeats);
		return deleteCartSeatsStatus;
	}

}
