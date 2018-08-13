# UserManager
user and user type management REST API using spring boot and MySQL

[<img src='https://spring.io/img/homepage/icon-spring-framework.svg' width=50>](https://spring.io/)
[<img src='https://spring.io/img/homepage/icon-spring-boot.svg' width=50>](https://spring.io/)
[<img src='https://upload.wikimedia.org/wikipedia/en/thumb/6/62/MySQL.svg/125px-MySQL.svg.png' width=70>](https://www.mysql.com/)
[<img src='http://www.howcsharp.com/img/0/52/hibernate-orm-300x223.jpg' width=50>](http://hibernate.org/)
[<img src='https://indrabasak.files.wordpress.com/2016/04/swagger.png?w=225' width=50>](https://swagger.io/)
[<img src='https://junit.org/junit4/images/junit-logo.png' width=70>](hhttps://junit.org/junit4/)
[<img src='http://3.bp.blogspot.com/-CsXsVyB_rcs/VHIqzQG3fjI/AAAAAAAAAxs/GfOaCWquZYg/s1600/mockito_logo-320x240.png' width=70>](https://site.mockito.org)

# Runing instructions
## Edit configurations
1. Open **src/main/resources/application.properties**.
2. Add database credentials to the database setup section. set **spring.jpa.hibernate.ddl-auto=create** for initial run.
3. Do port and debug logging configurations at app configuration section.
4. Add users to the last section in aorder to authenticate by basic authentication. Make sure you set the **user count**.
## Run by source code
Browse the root directory that contains pom.xml and run:
```sh
mvn clean install
mvn spring-boot:run
```
## Run by JAR executable.
Copy paste new **application.properties** to **target/** folder to inject configurations in to executable. run:
```sh
jar uf  usermanager-1.0-SNAPSHOT.jar  application.properties
java -jar usermanager-1.0-SNAPSHOT.jar
```
After initial run, set **spring.jpa.hibernate.ddl-auto=none** and save/ inject application.properties file and restart the server.
