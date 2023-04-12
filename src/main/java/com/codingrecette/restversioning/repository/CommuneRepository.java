package com.codingrecette.restversioning.repository;

import com.codingrecette.restversioning.model.Commune;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommuneRepository {

	private List<Commune> communes = new ArrayList<>();
	
	public Commune add(Commune commune) {
		communes.add(commune);
		return commune;
	}
	
	public Commune update(Commune commune) {
		communes.set(communes.indexOf(commune), commune);
		return commune;
	}
	
	public Commune update(Long codePostal, Commune commune) {
		Optional<Commune> communeToUpdate = communes.stream().filter(c -> codePostal.equals(c.getCodePostal())).findFirst();
		if (communeToUpdate.isPresent()) {
			communes.set(communes.indexOf(communeToUpdate.get()), commune);
		}
		return commune;
	}
	
	public Commune findById(Long codePostal) {
		Optional<Commune> commune = communes.stream().filter(c -> codePostal.equals(c.getCodePostal())).findFirst();
		if (commune.isPresent())
			return commune.get();
		else
			return null;
	}
	
	public void delete(Long codePostal) {
		communes.remove(codePostal.intValue());
	}
	
}
