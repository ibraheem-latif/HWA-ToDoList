package com.qa.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.domain.ToDoItem;
import com.qa.main.services.ItemServices;

@RestController
@CrossOrigin 
@RequestMapping("/item")
public class ItemController {

	private ItemServices service;

	public ItemController(ItemServices service) {
		this.service = service;
	}

	// POST REQUESTS - CREATE
	@PostMapping("/create")
	public ResponseEntity<ToDoItem> create(@RequestBody ToDoItem item) {
		
		return new ResponseEntity<ToDoItem>(service.create(item), HttpStatus.CREATED);

	}

	// GET REQUESTS - READ
	@GetMapping("/getAll")
	public ResponseEntity<List<ToDoItem>> getAll() {
		return new ResponseEntity<List<ToDoItem>>(service.getAll(), HttpStatus.OK);
	}

	@GetMapping("/getByID/{id}")
	public ResponseEntity<ToDoItem> getByID(@PathVariable long id) {
		return new ResponseEntity<ToDoItem>( service.getByID(id), HttpStatus.OK);
	}
	
	//Custom Query 
	@GetMapping("/getByCategory/{category}")
	public ResponseEntity<List<ToDoItem>> getByCategory(@PathVariable String category){
		return new  ResponseEntity<List<ToDoItem>> ( service.getByCategory(category),HttpStatus.OK);
	}

	// PUT REQUESTS - UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable long id, @RequestBody ToDoItem item) {
		return new ResponseEntity<ToDoItem>( service.updateToDoItem(id, item),HttpStatus.ACCEPTED);
	}

	// DELETE REQUESTS - DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> removeToDoItem(@PathVariable long id) {
		return new ResponseEntity<Boolean>(service.removeToDoItem(id), HttpStatus.NO_CONTENT);
	}

}
