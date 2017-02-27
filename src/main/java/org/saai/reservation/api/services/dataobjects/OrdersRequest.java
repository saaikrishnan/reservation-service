package org.saai.reservation.api.services.dataobjects;

public class OrdersRequest {
	private int showTimingId;
	private int[] seatIds;
	private String uuid;

	public OrdersRequest() {

	}

	public OrdersRequest(int showTimingId, int[] seatIds, String uuid) {
		super();
		this.showTimingId = showTimingId;
		this.seatIds = seatIds;
		this.uuid = uuid;
	}

	public int getShowTimingId() {
		return showTimingId;
	}

	public void setShowTimingId(int showTimingId) {
		this.showTimingId = showTimingId;
	}

	public int[] getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(int[] seatIds) {
		this.seatIds = seatIds;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
