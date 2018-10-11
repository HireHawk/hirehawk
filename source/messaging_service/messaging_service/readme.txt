#Spring Hibernate + Spring Boot + Spring Security basic project

Note: project contains only user example to ensure that everything works

1)download project and open it via Intellij Idea

2)MySQL has to be installed on PC(recomended version 5.7)

3)create new database "testdb"

4)run following script:

CREATE TABLE testdb.test_table (

id INT NOT NULL,

user VARCHAR(45) NOT NULL,

PRIMARY KEY (id),

UNIQUE INDEX id_UNIQUE (id ASC));

3)go to basic_microservice/basic_service/src/main/resources/application.properties and change value of spring.datasource.password to your password in MySQL

4)run project as Spring Boot App