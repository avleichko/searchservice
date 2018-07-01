# Project Title

Search service, this service stores uploaded csv files and gives ability to search if content exists in file 

## Getting Started

```

cd <your_path_to>/searchservice
mvn clean install
java -jar service-0.0.1-SNAPSHOT.jar

open in your browser
http://localhost:8081/swagger-ui.html
```

### Prerequisites
service was designed to work with service ui. it can be downloaded here:

```
https://github.com/avleichko/search-service-ui.git
```

 java environment :
```
java >= 1.8
mvn >= 3.5.3

```

### limitations
upload file limitation. these limitations could be changed in application.yml

```
max-file-size: 30MB
max-request-size: 40MB

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
