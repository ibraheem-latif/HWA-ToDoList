package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.ToDoItem;
import com.qa.main.services.ItemServices;

@WebMvcTest
public class ToDoItemControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private ItemServices service;

	@Test
	void createTest() throws Exception {
		// Create an object for posting

		ToDoItem entry = new ToDoItem("Clothes", "T-shirt", false);
		String entryAsJSON = mapper.writeValueAsString(entry);

		// Create an object for checking the result
		ToDoItem result = new ToDoItem(2L, "Clothes", "T-shirt", false);
		String resultAsJSON = mapper.writeValueAsString(result);

		Mockito.when(service.create(entry)).thenReturn(result);

		mvc.perform(post("/item/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	void updateTest() throws Exception {
		//Create an Object to check result
		ToDoItem result = new ToDoItem(1L, "Clothes", "T-shirt", false);
		String resultAsJSON = mapper.writeValueAsString(result);
		
		Mockito.when(service.updateToDoItem(1L, result)).thenReturn(result);
		
		mvc.perform(put("/item/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(resultAsJSON))
			.andExpect(content().json(resultAsJSON));
	}
	

	

}
