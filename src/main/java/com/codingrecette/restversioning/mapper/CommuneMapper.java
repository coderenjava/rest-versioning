package com.codingrecette.restversioning.mapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.codingrecette.restversioning.model.CommuneV2;
import com.codingrecette.restversioning.model.CommuneV1;

@Component
public class CommuneMapper {

	public CommuneV2 map(CommuneV1 commune) {
		if (Objects.nonNull(commune)) {
			int age = Period.between(commune.getAnciennete(), LocalDate.now()).getYears();
			return new CommuneV2(commune.getCodePostal(), commune.getNom(), age);
		}
		return null;
	}
	
}
