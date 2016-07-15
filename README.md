# Spring Security JWT Rest sample

Sample Spring MVC Rest api application secured with spring security using JWT Tokens.
Creates a WAR file of the sample application.
 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

```sh
$ mkdir ~/spring && cd ~/spring
$ mkdir -p ~/spring/workspace
$ git clone https://github.com/staalla/spring-security-jwt-rest.git && cd spring-security-jwt-rest
```
Import the project into Eclipse (~/spring/workspace) to review and make any changes
```sh
$ mvn eclipse:eclipse
```

Deploy the war and note host:port (localhost:8380) and context root (restauth) to test
```sh
$ mvn clean package
```

Try the API without token it should result in 401 response
```sh
curl -X GET -H "Cache-Control: no-cache" "http://localhost:8380/restauth/api/message"
```

Try the API with token (org.staalla.spring.security.jwt.rest.demo.JwtDemo to create token)
```sh
curl -X GET -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdGFhbGxhIiwidXNlcklkIjoiMSIsInJvbGUiOiJyb2xlIn0.mjZAI68kOsH8-KFFEzfol6PnK8qNb-zkIb1H9_cPtE0XVNJGUzIMujg_k1hFibLQXRQleRTXP9Uinsx84LVGFw" -H "Cache-Control: no-cache" "http://localhost:8380/restauth/api/message" 
```

### Prerequisities

JDK1.7, Git, Maven, Eclipse

### Acknowledgements

https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java

