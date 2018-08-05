# security-proof-of-concept

Spring security with configures authentication provider and filter.

Followed the authentication steps in SECURING A REST API WITH SPRING SECURITY (see https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/)

Running the following results in a 401 unauthorized status
```
curl localhost:8080/widgets
```

Running the following results in a 200 result status
```
curl -H "Authorization: Bearer 123456789" localhost:8080/widgets
```
