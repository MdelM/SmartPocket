package com.smartpocket.android.model;

import java.util.Date;


public class Stock {

	private int id;
	private String idArticle;
	private double qteIn;
	private double qteOut;
	private Date datestock;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}
	public double getQteIn() {
		return qteIn;
	}
	public void setQteIn(double qteIn) {
		this.qteIn = qteIn;
	}
	public double getQteOut() {
		return qteOut;
	}
	public void setQteOut(double qteOut) {
		this.qteOut = qteOut;
	}
	public Date getDatestock() {
		return datestock;
	}
	public void setDatestock(Date datestock) {
		this.datestock = datestock;
	}
	
	
	
	
}
