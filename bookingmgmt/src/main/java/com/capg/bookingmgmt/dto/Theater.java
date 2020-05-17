package com.capg.bookingmgmt.dto;

import java.util.List;
import com.capg.bookingmgmt.dto.Screen;

public class Theater {
	private int theaterId;
	private String theaterName;
	private String theaterCity;
	private List<Integer> movieIds;
	private List<Screen> screenList;
	private String managerName;
	private String managerContact;
	
	
	public Theater(int theaterId, String theaterName, String theaterCity, List<Integer> movieIds,
			List<Screen> screenList, String managerName, String managerContact) {
		super();
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterCity = theaterCity;
		this.movieIds = movieIds;
		this.screenList = screenList;
		this.managerName = managerName;
		this.managerContact = managerContact;
	}
	public int getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getTheaterCity() {
		return theaterCity;
	}
	public void setTheaterCity(String theaterCity) {
		this.theaterCity = theaterCity;
	}
	public List<Integer> getMovieIds() {
		return movieIds;
	}
	public void setMovieIds(List<Integer> movieIds) {
		this.movieIds = movieIds;
	}
	public List<Screen> getScreenList() {
		return screenList;
	}
	public void setScreenList(List<Screen> screenList) {
		this.screenList = screenList;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerContact() {
		return managerContact;
	}
	public void setManagerContact(String managerContact) {
		this.managerContact = managerContact;
	}
	
}
