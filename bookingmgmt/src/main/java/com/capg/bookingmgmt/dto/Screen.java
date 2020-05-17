package com.capg.bookingmgmt.dto;

import java.util.List;

public class Screen {
	private int screenId;
	private int theaterId;
	private String screenName;
	private List<Integer> showList;
	private int row;
	private int column;
	
	public Screen(int screenId, int theaterId, String screenName, List<Integer> showList, int row, int column) {
		super();
		this.screenId = screenId;
		this.theaterId = theaterId;
		this.screenName = screenName;
		this.showList = showList;
		this.row = row;
		this.column = column;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public int getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public List<Integer> getShowList() {
		return showList;
	}
	public void setShowList(List<Integer> showList) {
		this.showList = showList;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
}
