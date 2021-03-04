package com.qa.museum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.museum.domain.Specimen;

@Repository
public interface SpecimenRepo extends JpaRepository<Specimen, Long> {

}
