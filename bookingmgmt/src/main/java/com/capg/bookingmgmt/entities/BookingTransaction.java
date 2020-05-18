package com.capg.bookingmgmt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookingTransaction {
	@Id
	@GeneratedValue
	private int transactionId;
	private String transactionMethod;
	private double transactionAmount;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionMethod() {
		return transactionMethod;
	}
	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	@Override
	public int hashCode() {
		String transactionIdString = ""+transactionId;
		return transactionIdString.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null || obj instanceof BookingTransaction)
			return false;
		BookingTransaction other = (BookingTransaction) obj;
		return this.transactionId == other.transactionId;
	}
	
	
}
