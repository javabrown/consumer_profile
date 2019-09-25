Consumer-Profile API
====================

This HATEOAS Driven REST-3 APIs application. I developed this project as part of my **ConsumerReport.** assignment. It serves CRUD operation on UserProfile Resource.


**Following tools and technology I have used:**

* Java 8
* SpringBoot 2.1 & Spring HATEOAS 0.25.2
* JPA & H2Database
* Springfox-swagger 2.9
* AWS ECS, Docker, ECR
* Terraform (for resource creation & deployment)
* Linux, IntelliJ, Maven

**Core Application Flow Diagram**:

![alt text](https://raw.githubusercontent.com/javabrown/consumer_profile/master/src/main/resources/docs/basic-flow.png)


-------
**Core Application Flow Diagram**:

|Method                |API                            |Description                         |
|----------------|-------------------------------|-----------------------------|
|GET             |`/api/users `                  |Returns all user             |
|POST            |`/api/users/`                  |Create new user              |
|PUT             |`/api/users/{user-id}`         |Update user info by ID|
|DELETE          |`/api/users/{user-id}`         |Delete the user for given ID |
|GET             |`/api/users/{user-id}`         |Returns individual user for given ID |

----


#### Live in AWS:
* Live API demo: **[HERE](http://35.174.137.88/api/users/)**
* Swagger Documentation:  **[HERE](http://35.174.137.88/api/swagger-ui.html#/user-resource)**

----

##### Build Instruction:
* Package: ``mvn clean test package``
* Run: ``java -jar target/consumer_profile-xx.jar``
* Make Docker Image: `` docker build -t consumer_profile .`` 

