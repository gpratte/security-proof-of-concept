# security-proof-of-concept

Spring security starts running in basic authentication mode.

Running the following results in a 401 unauthorized status
```
curl localhost:8080/widgets
```

Running the following (substitute in the basic auth password from the spring boot log when starting) results in a 200 result status
```
curl -u user:99dddba0-ca41-4ee0-ae15-cdfb2a36f060 localhost:8080/widgets
```
