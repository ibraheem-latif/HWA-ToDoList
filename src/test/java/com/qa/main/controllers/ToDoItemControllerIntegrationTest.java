package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.ToDoItem;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql","classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // Switching to H2 for the test
public class ToDoItemControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;// Used for converting objects to JSON

	@Test
	public void createTest() throws Exception {
		// Create an object for posting

		ToDoItem entry = new ToDoItem("Clothes", "T-shirt", false);
		String entryAsJSON = mapper.writeValueAsString(entry);

		// Create an object for checking the result
		ToDoItem result = new ToDoItem(2L, "Clothes", "T-shirt", false);
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(post("/item/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void readAllTest() throws Exception{
		
		//Create a list to check the output of readAll
		List<ToDoItem> result = new ArrayList<>();
		// Add the single entry to the list
		result.add(new ToDoItem(1L, "Shopping","milk", false));
		// Convert the list to JSON (The API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(get("/item/getAll")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void readByIdTest() throws Exception {
		ToDoItem ToDoItem = new ToDoItem(1L, "Shopping","milk", false);
		String ToDoItemJSON = mapper.writeValueAsString(ToDoItem);
		
		mvc.perform(get("/item/getByID/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(ToDoItemJSON));
		
	}
	
	@Test
	public void readByCategoryTest() throws Exception {
		
		List<ToDoItem> result = new ArrayList<>();
		// Add the single entry to the list
		result.add(new ToDoItem(1L, "Shopping","milk", false));
		// Convert the list to JSON (The API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(get("/item/getByCategory/Shopping")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
		
	}

	@Test
	public void updateTest() throws Exception {
		
		ToDoItem update = new ToDoItem(1L, "Shopping","milk", true);
		String updateJSON = mapper.writeValueAsString(update);
		
		ToDoItem expected = new ToDoItem(1L, "Shopping","milk", true);
		String expectedJSON = mapper.writeValueAsString(expected);
		
		mvc.perform(put("/item/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updateJSON))
				.andExpect(content().json(expectedJSON));
		
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/item/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	} 

}
