package com.codingrecette.restversioning.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Commune")
public class CommuneV2 extends Commune {

	private int age;

	public CommuneV2() {

	}

	public CommuneV2(Long codePostal, String nom, int age) {
		super(codePostal, nom);
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
