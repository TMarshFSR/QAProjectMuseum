package com.qa.museum.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.museum.domain.Specimen;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")

@Sql(scripts = { "classpath:specimen-schema.sql",
		"classpath:specimen-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class SpecimenControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Specimen newSpecimen = new Specimen("Tyrannosaurus_Rex", "California, USA", "Container_A",
				LocalDate.of(2020, 05, 02), "Jaw Bone");

		String newSpecimenAsJSON = this.mapper.writeValueAsString(newSpecimen);
		RequestBuilder mockRequest = post("/createSpecimen").contentType(MediaType.APPLICATION_JSON)
				.content(newSpecimenAsJSON);

		Specimen savedSpecimen = new Specimen(2L, "Tyrannosaurus_Rex", "California, USA", "Container_A",
				LocalDate.of(2020, 05, 02), "Jaw Bone");
		String savedSpecimenAsJSON = this.mapper.writeValueAsString(savedSpecimen);

		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedSpecimenAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

	}

	@Test
	void readTest() throws Exception {
		Specimen testSpecimen = new Specimen(1L, "archaeornithymimus", "Peru", "Container-C",
				LocalDate.of(2015, 10, 07), "skeleton");

		List<Specimen> allSpecimens = List.of(testSpecimen);
		String testSpecimenAsJSON = this.mapper.writeValueAsString(allSpecimens);

		RequestBuilder mockRequest = get("/getSpecimens");

		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(testSpecimenAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

	}

	@Test
	void updateTest() throws Exception {
		Specimen updateSpecimen = new Specimen("Velociraptor", "Utah, USA", "Main Hall", LocalDate.of(2017, 03, 02),
				"Complete Skeleton");
		String updateSpecimenAsJSON = this.mapper.writeValueAsString(updateSpecimen);

		RequestBuilder mockRequest = put("/updateSpecimen/1").contentType(MediaType.APPLICATION_JSON)
				.content(updateSpecimenAsJSON);

		Specimen savedSpecimen = new Specimen(1L, "Velociraptor", "Utah, USA", "Main Hall", LocalDate.of(2017, 03, 02),
				"Complete Skeleton");
		String savedSpecimenAsJSON = this.mapper.writeValueAsString(savedSpecimen);

		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(savedSpecimenAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

	}

	@Test
	void deleteTest() throws Exception {

		RequestBuilder mockRequest = delete("/removeSpecimen/1");

		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().string("true");

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

}
