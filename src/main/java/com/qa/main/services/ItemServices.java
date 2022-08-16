package com.qa.main.services;

import java.util.List;


import org.springframework.stereotype.Service;


import com.qa.main.domain.ToDoItem;
import com.qa.main.exceptions.CategoryNotFoundException;
import com.qa.main.repos.ItemRepo;

@Service
public class ItemServices {

	private ItemRepo repo;

	public ItemServices(ItemRepo repo) {
		this.repo = repo;
	}

	public ToDoItem create(ToDoItem item) {

		return repo.saveAndFlush(item);
	}

	public List<ToDoItem> getAll() {
		return repo.findAll();

	}

	public ToDoItem getByID(long id) {
		return repo.findById(id).orElseThrow(CategoryNotFoundException::new);

	}
	
	// Custom Query
	public List<ToDoItem> getByCategory(String category){
		return repo.findToDoItemByCategory(category);
	}

	public ToDoItem updateToDoItem(long id, ToDoItem item) {
		// We get the existing entry
		ToDoItem existing = repo.findById(id).get();

		// Update the existing entry, to match the incoming object
		existing.setCategory(item.getCategory());
		existing.setName(item.getName());
		existing.setComplete(item.isComplete());

		// Save the updated entry back into the DB (ID is the same)
		return repo.saveAndFlush(existing);

	}

	public boolean removeToDoItem(long id) {
		repo.deleteById(id);
		return !repo.existsById(id);

	}
	
}
