package com.qa.museum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.museum.domain.Specimen;
import com.qa.museum.repo.SpecimenRepo;

@Service
public class SpecimenServiceDB implements SpecimenService {

	private SpecimenRepo repo;

	public SpecimenServiceDB(SpecimenRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Specimen createSpecimen(Specimen specimen) {
		Specimen saved = this.repo.save(specimen);
		return saved;
	}

	@Override
	public List<Specimen> getSpecimen() {
		return this.repo.findAll();
	}

	@Override
	public Specimen getSpecimenById(Long id) {
		Optional<Specimen> optSpecimen = this.repo.findById(id);
		return optSpecimen.get();
	}

	@Override
	public Specimen getByLatinName(String latinName) {
		return this.repo.findByLatinName(latinName);
	}

	@Override
	public boolean removeSpecimen(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	@Override
	public Specimen updateSpecimen(Long id, Specimen newSpecimen) {
		Optional<Specimen> optionalSpecimen = this.repo.findById(id);
		Specimen existing = optionalSpecimen.get();

		existing.setLatinName(newSpecimen.getLatinName());
		existing.setOrigin(newSpecimen.getOrigin());
		existing.setDateArrived(newSpecimen.getDateArrived());
		existing.setStorageLocation(newSpecimen.getStorageLocation());
		existing.setDescription(newSpecimen.getDescription());

		Specimen updated = this.repo.save(existing);
		return updated;
	}

}
