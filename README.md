# Master MVC App

This is a simple CRUD, MVC app

### Technologies used
* Java
* Spring Boot
* Hibernate
* Thymeleaf
* Bootsrap

### Prerequisites
* Git
 ```sh
 sudo apt-get install git
 ```
* JDK 1.8 
```sh 
sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo apt-get install oracle-java8-set-default
```
* Maven
```sh
sudo apt-get install maven
```
 Make sure you have installed all the prerequisites, To check, run following commands
 * java -version
 * mvn -version
 * git version
### Installation

* Clone this project
```sh
git clone https://github.com/zhoodar/master-mvcapp.git
```
* Build Application
```sh
cd master-mvcapp
mvn clean install
mvn spring-boot:run - To run in dev mode
cd target
java -jar master-0.0.1-SNAPSHOT.jar 
```
### Try it

- Now open your browser: http://localhost:8080/
