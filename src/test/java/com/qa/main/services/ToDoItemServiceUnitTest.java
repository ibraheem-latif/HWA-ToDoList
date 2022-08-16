package com.qa.main.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.ToDoItem;
import com.qa.main.repos.ItemRepo;

@SpringBootTest
public class ToDoItemServiceUnitTest {
	
	@Autowired
	private ItemServices service;
	
	@MockBean
	private ItemRepo repo;
	
	@Test
	public void testCreate() throws Exception {
		// Create an object for saving

		ToDoItem entry = new ToDoItem("Clothes", "T-shirt", false);

		// Create an object for checking the result
		ToDoItem result = new ToDoItem(2L, "Clothes", "T-shirt", false);
		
		Mockito.when(repo.saveAndFlush(entry)).thenReturn(result);
		
		assertEquals(result, service.create(entry));
		

}
	@Test
	public void testGetAll() {
		//Create an object for saving
		List<ToDoItem> result = new ArrayList<>();
		result.add(new ToDoItem(1L,"Shopping","milk",false));
		
		Mockito.when(repo.findAll()).thenReturn(result);
		
		assertEquals(result,service.getAll());
		
		
	}
	
	@Test
	public void getByIdTest() {
		
		long id = 1;
		//Create an object for saving
		ToDoItem result = new ToDoItem(1L, "Shopping","milk", false);
		Optional<ToDoItem> resultI = Optional.of(result);
		Mockito.when(repo.findById(id)).thenReturn(resultI);
		
		assertEquals(result, service.getByID(id));
		
		
	}
	
	@Test
	public void updateByIdTest() {
		long id =1L;
		ToDoItem entry = new ToDoItem("Clothes", "cap", true);	
		
		ToDoItem existing = new ToDoItem(1L, "Shopping","milk", false);
		Optional<ToDoItem> existingI = Optional.of(existing);
		Mockito.when(repo.findById(id)).thenReturn(existingI);
		
		ToDoItem update = new ToDoItem(1L,"Clothes", "cap", true);
		
		Mockito.when(repo.saveAndFlush(update)).thenReturn(update);
		assertEquals(update, service.updateToDoItem(id,entry));
	}
	
}
