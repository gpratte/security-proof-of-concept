# security-proof-of-concept

Spring security with configures authentication provider and filter.

Running the following results in a 401 unauthorized status
```
curl localhost:8080/widgets
```

Running the following (substitute in the basic auth password from the spring boot log when starting) results in a 200 result status
```
curl -H "Authorization: Bearer 123456789" localhost:8080/widgets
```
