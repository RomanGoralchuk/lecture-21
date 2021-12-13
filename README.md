# Hometask #21
### Description
Hibernate Spring practices
### Task List
Home task #21
1. git repo and best practices
2. base on #20
3. add DAO and hibernate tiers with spring integration
4. all configurations in Java code (don't use xml)
5. deploy to servlet container per student
+ [Note: see example](https://github.com/vladislav-sidorovich/web-service-example)

### Technologies
* Java 11
* Maven
* FlyWay
* Slf4j+Logback
* MariaDB
* DBPool Apache
* Mockito
* JUnit4
* Docker-compose
* Hibernate
* Jetty

### How to run
1. Start scripts:
* `mvn clean package`
* `docker-compose up`
* `mvn compile flyway:migrate`
2. Start servlet container and deploy war-file
