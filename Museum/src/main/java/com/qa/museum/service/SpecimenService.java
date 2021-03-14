package com.qa.museum.service;

import java.util.List;

import com.qa.museum.domain.Specimen;

public interface SpecimenService {

	Specimen createSpecimen(Specimen specimen);

	List<Specimen> getSpecimen();

	Specimen getSpecimenById(Long id);

	boolean removeSpecimen(Long id);

	Specimen updateSpecimen(Long id, Specimen newSpecimen);

	Specimen getByLatinName(String latinName);

}
