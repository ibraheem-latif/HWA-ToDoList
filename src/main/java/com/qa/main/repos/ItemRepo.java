package com.qa.main.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.ToDoItem;

@Repository 
public interface ItemRepo extends JpaRepository<ToDoItem, Long> {
	
	//SELECT * FROM to_do_list WHERE category ={category}
	//--------------findEntityVariable---------------------
	List<ToDoItem> findToDoItemByCategory(String category);
}
