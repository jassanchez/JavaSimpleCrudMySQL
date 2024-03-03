# Crud Java Using Maven JDBC

A simple crud with Java 21 (JDK Corretto) using Maven to integrate MySQL Connector dependency to the project.

This project was created with Visual Studio Code with Java Extensions.

## Installation

Create this table for use the project with MySQL.

```SQL
CREATE TABLE `users2` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
)
```

If you don't have installed Java, the link below redirect you to official page of Amazon Corretto JDk
[Amazon JDK Corretto](https://aws.amazon.com/es/corretto)