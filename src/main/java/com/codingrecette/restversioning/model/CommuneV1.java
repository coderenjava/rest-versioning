package com.codingrecette.restversioning.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "Commune")
public class CommuneV1 extends Commune {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate anciennete;

	public CommuneV1() {

	}	
	
	public CommuneV1(Long codePostal, String nom,LocalDate anciennete) {
		super(codePostal, nom);
		this.anciennete = anciennete;
	}

	public LocalDate getAnciennete() {
		return anciennete;
	}

	public void setAnciennete(LocalDate anciennete) {
		this.anciennete = anciennete;
	}

}
