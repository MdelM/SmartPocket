package com.smartpocket.android.model;

import java.util.Date;

public class Depense {

	private String type;
	private double amount;
	private Date datedepense;
	private String description;
	private String lieu;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDatedepense() {
		return datedepense;
	}
	public void setDatedepense(Date datedepense) {
		this.datedepense = datedepense;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	
	
}
