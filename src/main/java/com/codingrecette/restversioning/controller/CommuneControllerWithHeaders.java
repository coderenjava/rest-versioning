package com.codingrecette.restversioning.controller;

import com.codingrecette.restversioning.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codingrecette.restversioning.mapper.CommuneMapper;
import com.codingrecette.restversioning.model.CommuneV1;
import com.codingrecette.restversioning.model.CommuneV2;

@RestController
@RequestMapping("/commune-custom-header")
public class CommuneControllerWithHeaders {

	@Autowired
	CommuneMapper mapper;
	@Autowired
	CommuneRepository repository;

	@PostMapping(headers = "X-VERSION=v1.0")
	public CommuneV1 add(@RequestBody CommuneV1 commune) {
		return (CommuneV1) repository.add(commune);
	}

	@PostMapping(headers = "X-VERSION=v1.2")
	public CommuneV2 add(@RequestBody CommuneV2 commune) {
		return (CommuneV2) repository.add(commune);
	}
	
	@PutMapping(headers = "X-VERSION=v1.0")
	@Deprecated
	public CommuneV1 update(@RequestBody CommuneV1 commune) {
		return (CommuneV1) repository.update(commune);
	}
	
	@PutMapping(value = "/{codePostal}", headers = "X-VERSION=v1.1")
	public CommuneV1 update(@PathVariable("codePostal") Long codePostal, @RequestBody CommuneV1 commune) {
		return (CommuneV1) repository.update(commune);
	}
	
	@PutMapping(value = "/{codePostal}", headers = "X-VERSION=v1.2")
	public CommuneV2 update(@PathVariable("codePostal") Long codePostal, @RequestBody CommuneV2 commune) {
		return mapper.map((CommuneV1) repository.update(commune));
	}
	
	@GetMapping(value = "/{codePostal}", headers = "X-VERSION=v1.0")
	public CommuneV1 findByIdOld(@PathVariable("codePostal") Long codePostal) {
		return (CommuneV1) repository.findById(codePostal);
	}

	@GetMapping(value = "/{codePostal}", headers = "X-VERSION=v1.2")
	public CommuneV2 findById(@PathVariable("codePostal") Long codePostal) {
		return mapper.map((CommuneV1) repository.findById(codePostal));
	}
	
	@DeleteMapping("/{codePostal}")
	public void delete(@PathVariable("codePostal") Long codePostal) {
		repository.delete(codePostal);
	}
	
}
