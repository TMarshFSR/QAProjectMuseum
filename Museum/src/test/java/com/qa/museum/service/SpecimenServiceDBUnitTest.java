package com.qa.museum.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.museum.domain.Specimen;
import com.qa.museum.repo.SpecimenRepo;

@ActiveProfiles("test")
@SpringBootTest
public class SpecimenServiceDBUnitTest {

	@Autowired
	private SpecimenServiceDB service;

	@MockBean
	private SpecimenRepo repo;

	@Test
	void testCreate() {

		Specimen newSpecimen = new Specimen("Tyrannosaurus_Rex", "California, USA", "Container_A",
				LocalDate.of(2020, 05, 02), "Jaw Bone");
		Specimen savedSpecimen = new Specimen(1L, "Tyrannosaurus_Rex", "California, USA", "Container_A",
				LocalDate.of(2020, 05, 02), "Jaw Bone");

		Mockito.when(this.repo.save(newSpecimen)).thenReturn(savedSpecimen);

		assertThat(this.service.createSpecimen(newSpecimen)).isEqualTo(savedSpecimen);

		Mockito.verify(this.repo, Mockito.times(1)).save(newSpecimen);
	}

	@Test
	void testUpdate() {

		Long id = 1L;
		Specimen newSpecimen = new Specimen("Chimpanzee", "Bourneo", "Container_C", LocalDate.of(2018, 04, 29),
				"Fully preserved specimen");
		Optional<Specimen> existingSpecimen = Optional.of(new Specimen(id, null, null, null, null, null));
		Specimen updatedSpecimen = new Specimen(id, newSpecimen.getLatinName(), newSpecimen.getOrigin(),
				newSpecimen.getStorageLocation(), newSpecimen.getDateArrived(), newSpecimen.getDescription());

		Mockito.when(this.repo.findById(id)).thenReturn(existingSpecimen);
		Mockito.when(this.repo.save(updatedSpecimen)).thenReturn(updatedSpecimen);

		assertThat(this.service.updateSpecimen(id, newSpecimen)).isEqualTo(updatedSpecimen);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedSpecimen);
	}

	@Test
	void testRead() {

		Specimen newSpecimen = new Specimen("Tyrannosaurus_Rex", "California, USA", "Container_A",
				LocalDate.of(2020, 05, 02), "Jaw Bone");
		List<Specimen> allSpecimens = List.of(newSpecimen);

		Mockito.when(this.repo.findAll()).thenReturn(allSpecimens);

		assertThat(this.service.getSpecimen()).isEqualTo(allSpecimens);
	}

	@Test
	void testDelete() {

		Mockito.when(!this.repo.existsById(1L)).thenReturn(false);

		assertThat(this.service.removeSpecimen(1L)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}

	@Test
	void testReadByLatinName() {

		Specimen newSpecimen = new Specimen("Tyrannosaurus_Rex", "California, USA", "Container_A",
				LocalDate.of(2020, 05, 02), "Jaw Bone");
		String latinName = "Tyrannosaurus_Rex";

		Mockito.when(this.repo.findByLatinName(latinName)).thenReturn(newSpecimen);

		assertThat(this.service.getByLatinName(latinName)).isEqualTo(newSpecimen);

	}

}
