# SpringSecurityRESTTemplate
Useful template for developing RESTful applications with Spring and Spring Security.


This project contains a Spring Security project using JWT Tokens as a way of
providing persistence in stateless context of REST.

Database object relational mapping is handled by Hibernate.

It has a configurable CORS-filter defined and a simple CustomUserDetails-implementation
that can be used for example implementing a real cache and/or a database backed
authentication.

Backend is configured to redirect HTTP into HTTPS. Mappings are modular and can be changed in
application.properties.

Maven Clean Install triggers building for the frontend as well as Spring Boot.
