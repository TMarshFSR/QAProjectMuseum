package com.qa.museum.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.museum.domain.Specimen;
import com.qa.museum.service.SpecimenService;

@RestController
public class SpecimenController {

	private SpecimenService service;

	public SpecimenController(SpecimenService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createSpecimen")
	public ResponseEntity<Specimen> createSpecimen(@RequestBody Specimen specimen) {
		return new ResponseEntity<Specimen>(this.service.createSpecimen(specimen), HttpStatus.CREATED);
	}

	@GetMapping("/getSpecimen")
	public ResponseEntity<List<Specimen>> getSpecimen() {
		return ResponseEntity.ok(this.service.getSpecimen());
	}

	@GetMapping("/getSpecimen/{id}")
	public Specimen getSpecimenById(@PathVariable Long id) {
		return this.service.getSpecimenById(id);
	}

	@DeleteMapping("/removeSpecimen/{id}")
	public boolean removeSpecimen(@PathVariable Long id) {
		return this.service.removeSpecimen(id);
	}

	@PutMapping("/updateDino/{id}")
	public Specimen updateSpecimen(@PathVariable Long id, @RequestBody Specimen newSpecimen) {
		return this.service.updateSpecimen(id, newSpecimen);
	}

	@GetMapping("/getByLatinName/{latinName}")
	public Specimen getByLatinName(@PathVariable String latinName) {
		return this.service.getByLatinName(latinName);
	}

}
