# blog
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

Blog [Spring Boot](http://projects.spring.io/spring-boot/) application.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Installation
Clone the repository 

```shell
https://github.com/seoLeir/blog.git
```

## Description
The Blog application is a powerful and modern web application designed for creating and exchanging technological articles, ideas, and experiences in the field of information technology. 
It ensures high performance, reliability, and user convenience.
Key Features of the "Blog" Project:

1. **User Registration and Authentication**
  - Users can create accounts, log in, and manage their profiles, with authentication ensured through Spring Security for data security.
2. **Article Creation and Editing** 
  - Users can create, edit, and delete articles using an advanced text editor that supports formatting and image insertion for enhanced content.
3. **Comments and Feedback**
  - Users can comment on articles, engage in discussions, and provide feedback, fostering an active community around technology-related topics.
4. **Categories and Tags**
  - A system of categories and tags helps users organize their articles, facilitating content discovery through easy categorization.
5. **Search and Filtering**
  - Users can easily find articles through keyword searches and utilize filters to refine results.
6. **Security and Data Validation**
  - The project ensures user data security through Spring Security mechanisms and robust data validation, providing a secure and stable web application.
7. **Database Management with Liquibasе** 
  - Liquibase enables version control for the database, allowing easy changes to the structure and management of data migrations.
8. **Object Mapping with MapStruct**
  - MapStruct efficiently transforms objects between application layers, simplifying code and enhancing readability.
9. **PostgreSQL for Data Storage**
  - The project utilizes PostgreSQL as a database, ensuring reliable and efficient data storage.
10. **Testing with Testcontainers**
  - Integration with Testcontainers enables effective testing using containers, enhancing predictability and portability in the testing process.

The application aims to unite the technology community by providing a convenient and secure platform for knowledge and experience exchange.

## Technologies used
- Spring(Core, Boot, Data, Web, Security, Test): 3.1.3
- Hibernate: 6.2.7
- PostgreSQL: 15.0
- Gradle: 8.2.1
- Liquibase: 4.20.0
- Testcontainers: 1.17.6
- MapStruct: 1.5.5
- Lombok plugin: 8.1.0 

## Database diagram

![ERD diagram](https://github.com/seoLeir/blog/blob/master/blog-erd.png)

## Running the application
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `io.seoLeir.socialmedia.SocialMediaApplication` class from your IDE.

or run this shell command in application console
```shell
./gradlew bootRun
```

## Copyright

[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Released under the MIT License. See the [LICENSE](https://github.com/seoLeir/blog/blob/master/LICENCE.md) file.
