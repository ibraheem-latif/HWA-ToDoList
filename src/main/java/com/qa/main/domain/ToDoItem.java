package com.qa.main.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoItem {

	// Columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// @Column(nullable =false) // adds a not null constraint to the column. (this
	// column cannot be null)


	@Column(nullable = false)
	private String category; // creates a column called category with the datatype VARCHAR(255)

	@Column(nullable = false)
	private String name; // creates a column called name with the datatype VARCHAR(255)

	@Column(nullable = false)
	private boolean complete; // creates a column called complete with the datatype BOOLEAN

	// Constructors

	// Default constructor (for spring)
	public ToDoItem() {
	};

	// For creating (without ID)
	public ToDoItem(String category, String name, boolean complete) {
		super();
		this.category = category;
		this.name = name;
		this.complete = complete;
	}

	// For reading (with ID)
	public ToDoItem(long id, String category, String name, boolean complete) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.complete = complete;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	// Override methods
	// For testing
	@Override
	public int hashCode() {
		return Objects.hash(category, complete, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoItem other = (ToDoItem) obj;
		return Objects.equals(category, other.category) && complete == other.complete && id == other.id
				&& Objects.equals(name, other.name);
	}

	
	

}
