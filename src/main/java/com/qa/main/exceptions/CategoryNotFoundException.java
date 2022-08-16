package com.qa.main.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.I_AM_A_TEAPOT, reason = "Category not found with that ID")
public class CategoryNotFoundException extends EntityNotFoundException {
	
	
	

}
