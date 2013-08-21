package com.smartpocket.android.model;

import java.util.Date;

public class Recette {

	private double montant;
	private String origine;
	private Date daterecette;
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public Date getDaterecette() {
		return daterecette;
	}
	public void setDaterecette(Date daterecette) {
		this.daterecette = daterecette;
	}
	

}
