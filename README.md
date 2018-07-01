# Project Title

Search service, this service stores uploaded csv files and gives ability to search if content exists in file 

## Getting Started

```

cd <your_path_to>/searchservice
mvn clean install
java -jar service-0.0.1-SNAPSHOT.jar
```

### Prerequisites


```
java >= 1.8
mvn >= 3.5.3

```

## Running the tests

```
cd <your_path_to>/searchservice
mvn test

```

### Unit tests

testing layer developed using spring mockmvc

description below:
```
* ApplicationIT.java - tests checks if swagger is ok and service health is ok
* ServiceApplicationTests.java - checks if context is of
* SearchTests.java  - check if file search works fine
* FileUploadTests.java - checks if file can be uploaded
```

## Authors

* **Aleksandr Velichko** - *email* - [mrdezzdemon@gmail.com](mrdezzdemon@gmail.com)
