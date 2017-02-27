package org.saai.reservation.api.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.saai.reservation.api.services.dataobjects.CartTimeoutSeats;
import org.saai.reservation.api.services.dataobjects.IndividualShows;
import org.saai.reservation.api.services.dataobjects.Screens;
import org.saai.reservation.api.services.dataobjects.Seats;
import org.saai.reservation.api.services.dataobjects.SeatsCarts;
import org.saai.reservation.api.services.dataobjects.ShowDetails;
import org.saai.reservation.api.services.dataobjects.ShowSeats;
import org.saai.reservation.api.services.dataobjects.ShowSeatsCarts;
import org.saai.reservation.api.services.dataobjects.ShowTimings;
import org.saai.reservation.api.services.dataobjects.Shows;

public class ShowsDAOImpl {

	Database db = new Database();
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	public ShowSeats getShowSeatsByPTID(int showTimingId) {
		try {
			connection = db.getConnection();
			String query = "SELECT * FROM PerformanceSeats WHERE PTID like '" + showTimingId + "'";
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			int seatId, columnNumber, bookingStatus;
			String rowNumber;
			List<Seats> seats = new ArrayList<Seats>();
			while (resultSet.next()) {
				seatId = Integer.parseInt(resultSet.getString("PSID"));
				rowNumber = resultSet.getString("RowNumber");
				columnNumber = Integer.parseInt(resultSet.getString("SeatNumber"));

				bookingStatus = Integer.parseInt(resultSet.getString("BookingStatus"));
				seats.add(new Seats(seatId, rowNumber, columnNumber, bookingStatus));
			}
			ShowSeats showSeats = new ShowSeats(showTimingId, seats);
			return showSeats;

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ShowSeatsCarts getShowSeatsInCartsByPTID(int showTimingId) {
		try {
			connection = db.getConnection();
			String query = "SELECT PerformanceSeats.PSID,PerformanceSeats.CartStatus FROM PerformanceSeats WHERE PTID like '"
					+ showTimingId + "'";
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			int seatId, cartStatus;
			List<SeatsCarts> seatsCarts = new ArrayList<SeatsCarts>();
			while (resultSet.next()) {
				seatId = Integer.parseInt(resultSet.getString("PSID"));
				cartStatus = Integer.parseInt(resultSet.getString("CartStatus"));
				seatsCarts.add(new SeatsCarts(seatId, cartStatus));
			}
			ShowSeatsCarts showSeatsCarts = new ShowSeatsCarts(showTimingId, seatsCarts);
			return showSeatsCarts;

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IndividualShows getAllDetailsOfShow(int showId) {

		try {
			connection = db.getConnection();
			String countRecordsQuery = "Select count(PerformanceTime.PTID) from PerformanceTime inner join screen on PerformanceTime.SID=screen.SID inner join Performance on PerformanceTime.PID=Performance.PID where PerformanceTime.PID="
					+ showId;
			preparedStatement = connection.prepareStatement(countRecordsQuery);
			ResultSet countOfRecordsResultSet = preparedStatement.executeQuery();
			int countOfUniqueShowTimingIds = 0;
			boolean allSame = true;
			while (countOfRecordsResultSet.next()) {
				countOfUniqueShowTimingIds = countOfRecordsResultSet.getInt(1);
			}
			String query = "Select PerformanceTime.PID,Performance.PerformanceName,Performance.PerformanceDescription,date_format(performancetime.Date,'%m-%d-%Y') as Date,PerformanceTime.SID,screen.ScreenName,PerformanceTime.PTID,time_format(performancetime.Time,'%h:%i %p') as Time,PerformanceTime.TicketsAvailable from PerformanceTime inner join screen on PerformanceTime.SID=screen.SID inner join Performance on PerformanceTime.PID=Performance.PID where PerformanceTime.PID="
					+ showId;
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			String showName = "";
			List<Screens> screens = new ArrayList<Screens>();
			List<ShowDetails> showDetails = new ArrayList<ShowDetails>();
			List<ShowTimings> showTimings = new ArrayList<ShowTimings>();

			String date = "";
			String tempDate = "";
			String showDescription = "";
			int screenId = 0;
			int tempScreenId = 0;
			int c = 0;
			while (resultSet.next()) {
				showId = resultSet.getInt(1);
				showName = resultSet.getString(2);
				showDescription = resultSet.getString(3);
				date = resultSet.getString(4);
				screenId = resultSet.getInt(5);
				if (tempDate == "")
					tempDate = date;
				if (tempScreenId == 0)
					tempScreenId = screenId;
				if (date.equals(tempDate)) {
					if (!(screenId == tempScreenId)) {
						Screens screen = new Screens(tempScreenId, resultSet.getString(6), showTimings);
						screens.add(screen);
						showTimings = new ArrayList<ShowTimings>();
					} else {
						++c;
					}
				} else {
					Screens screen = new Screens(tempScreenId, resultSet.getString(6), showTimings);
					screens.add(screen);
					ShowDetails shows = new ShowDetails(tempDate, screens);
					showDetails.add(shows);
					showTimings = new ArrayList<ShowTimings>();
					screens = new ArrayList<Screens>();
					screen = new Screens(screenId, resultSet.getString(6), showTimings);
					screens.add(screen);
				}
				ShowTimings showTiming = new ShowTimings();
				showTiming.setShowTimingId(resultSet.getInt(7));
				showTiming.setShowTime(resultSet.getString(8));
				showTiming.setTicketsAvailable(resultSet.getInt(9));
				showTimings.add(showTiming);
				if (countOfUniqueShowTimingIds == c) {
					Screens screen = new Screens(tempScreenId, resultSet.getString(6), showTimings);
					screens.add(screen);
					ShowDetails shows = new ShowDetails(tempDate, screens);
					showDetails.add(shows);
					allSame = false;
				}
				tempDate = date;
				tempScreenId = screenId;
			}
			if (allSame) {
				ShowDetails showDetail = new ShowDetails(tempDate, screens);
				showDetails.add(showDetail);
				showTimings = new ArrayList<ShowTimings>();
			}
			IndividualShows individualShows = new IndividualShows(showId, showName, showDescription,
					new ArrayList<ShowDetails>(showDetails));
			return individualShows;
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Shows> getAllShowDetails() {
		try {
			connection = db.getConnection();
			String query = "SELECT * FROM Performance";
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			int showId;
			String showName, showDescription;
			List<Shows> shows = new ArrayList<Shows>();
			while (resultSet.next()) {
				showId = Integer.parseInt(resultSet.getString("PID"));
				showName = resultSet.getString("PerformanceName");
				showDescription = resultSet.getString("PerformanceDescription");
				shows.add(new Shows(showId, showName, showDescription));
			}
			return shows;
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateCartSeatSelectionStatus(int seatId, int cartStatus) {
		try {
			connection = db.getConnection();
			if ((cartStatus != 0) && (cartStatus != 1))
				return 3000;
			
			String query = "Select CartStatus from PerformanceSeats where PSId="+seatId;
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery(query);
			while(resultSet.next()){
				if((resultSet.getInt("CartStatus")==1)&&(cartStatus==1)){
					return 3001;
				}
			}
			query = "update performanceseats Set CartStatus=" + cartStatus + " where PSID=" + seatId;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return 200;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 9000;

	}

	public int removeTimeOutSeats(CartTimeoutSeats cartTimeoutSeats) {
		try {
			connection = db.getConnection();
			String allPSIDs = Arrays.toString(cartTimeoutSeats.getSeatIds()).replaceAll("\\[|\\]", "");
			String query = "update performanceseats Set CartStatus = 0 where PSID in (" + allPSIDs + ")";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return 200;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 9000;

	}

}
