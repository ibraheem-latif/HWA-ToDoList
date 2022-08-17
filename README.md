#### Coverage: 84.6%
# To-Do List Web Page
This repository is for developing a personal hobby web application for QA. In this case, I've opted to design a to-do list web page that allows users to create, read, update, and delete entries in their personal list in order to keep track of their progress on tasks they aim to do.


## Getting Started
The procedures below will get a local copy of the project up and running for development and testing purposes. Notes on how to deploy the project on a live system, including maven packaging as well as how the frontend will be loaded after the JAR has been executed, can be found under deployment.

### Prerequisites
#### Essential
*The Java SE Development kit is required and may be downloaded from here:
<br>https://www.oracle.com/java/technologies/downloads/<br>
*MySQL Community, as well as MySQL Workbench, is required:
<br>https://dev.mysql.com/downloads/windows/installer/8.0.html <br>

#### Additional
*Gitbash for terminal access:
 <br>https://git-scm.com/downloads <br>
*Spring boot IDE for API alterations:
<br>https://spring.io/tools <br>
*VS code for any CSS, HTML or Javascript changes:
<br>https://code.visualstudio.com/download <br>
*Apache Maven for testing and dependencies:
 <br>https://maven.apache.org/download.cgi <br>
 <br>[JUnit](https://mvnrepository.com/artifact/junit/junit) <br>
        [Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core) <br>




### Installing
The instructions for installing and running the application are as follows:
```
Clone the repository to your local computer at a secure location using gitBash.
```

```
In the project folder, run the JAR file SNAPSHOT 0.0.1.
```

```
maybe show a terminal example???
```

## Running the Tests
Right-click on the project in the Spring tool IDE and select "Coverage As" then "Junit," which will execute all of the tests. Alternatively, enter CMD and type "mvn clean install." This will run all of the tests and provide a coverage table indicating how much of the code was tested and what percentage passed/failed/erroneously.

### Unit Tests
Unit testing is a testing approach that uses conditions to test smaller isolated portions of code that may be utilised logically.

Unit testing was utilised in the project, below is an example for testing the ToDoItem controller method of create. Where it was implemented by utilising Mockito to imitate the activities of the function's dependencies. Because the create method relied on the ToDoItem service, Mocktio mimicked what happened when the method called the service. create.


```
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

```

### Integration Tests
Integration testing is the process of evaluating various coupled components of an application to check if they logically operate together and achieve the desired result. <br>
An example of integration testing in the controller class is shown below:

```
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
```



## Built with
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning
* [GitHub](https://github.com/) for versioning.

## Authors
* **Ibraheem Latif** - *initial work* - [Ibraheem-Latif](https://github.com/ibraheem-latif)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* *Trainer* - [AnoushLowton](https://github.com/ALowtonQA)
* *Trainer* - [JordanBenBelaid](https://github.com/jordanbenbelaid)
* *Trainer* - [Edward Reynolds](https://github.com/Edrz-96)
* *Trainer* - [Piers Barber](https://github.com/PCMBarber)
* *Testing Developer* - [JUnit](https://junit.org/junit5/docs/current/user-guide/#running-tests)
