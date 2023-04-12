package com.codingrecette.restversioning.model;

public abstract class Commune {

	private Long codePostal;
	private String nom;

	public Commune() {

	}
	
	public Commune(Long codePostal, String nom) {
		this.codePostal = codePostal;
		this.nom = nom;
	}

	public Long getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Long codePostal) {
		this.codePostal = codePostal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
