package com.codingrecette.restversioning.controller;

import com.codingrecette.restversioning.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codingrecette.restversioning.mapper.CommuneMapper;
import com.codingrecette.restversioning.model.CommuneV1;
import com.codingrecette.restversioning.model.CommuneV2;

@RestController
@RequestMapping("/commune")
public class CommuneController {

	@Autowired
	CommuneMapper mapper;
	@Autowired
	CommuneRepository repository;

	@PostMapping({"/v1.0", "/v1.1"})
	public CommuneV1 add(@RequestBody CommuneV1 commune) {
		return (CommuneV1) repository.add(commune);
	}

	@PostMapping("/v1.2")
	public CommuneV2 add(@RequestBody CommuneV2 commune) { return (CommuneV2) repository.add(commune); }
	
	@PutMapping("/v1.0")
	@Deprecated
	public CommuneV1 update(@RequestBody CommuneV1 commune) {
		return (CommuneV1) repository.update(commune);
	}
	
	@PutMapping("/v1.1/{codePostal}")
	public CommuneV1 update(@PathVariable("codePostal") Long codePostal, @RequestBody CommuneV1 commune) {
		return (CommuneV1) repository.update(commune);
	}
	
	@PutMapping("/v1.2/{codePostal}")
	public CommuneV2 update(@PathVariable("codePostal") Long codePostal, @RequestBody CommuneV2 commune) {
		return mapper.map((CommuneV1) repository.update(commune));
	}
	
	@GetMapping({"/v1.0/{codePostal}", "/v1.1/{codePostal}"})
	public CommuneV1 findByIdOld(@PathVariable("codePostal") Long codePostal) {
		return (CommuneV1) repository.findById(codePostal);
	}
	
	@GetMapping("/v1.2/{codePostal}")
	public CommuneV2 findById(@PathVariable("codePostal") Long codePostal) {
		return mapper.map((CommuneV1) repository.findById(codePostal));
	}
	
	@DeleteMapping({"/v1.0/{codePostal}", "/v1.1/{codePostal}", "/v1.2/{codePostal}"})
	public void delete(@PathVariable("codePostal") Long codePostal) {
		repository.delete(codePostal);
	}
	
}
