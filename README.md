# security-proof-of-concept

Spring security with configures authentication provider and filter.

Support three types of tokens:
* tkn1
* tkn2
* tkn3

And these types of roles
* User
* Super User
* Admin
* tkn1
* tkn2
* tkn3

As you can see the roles have the normal types (e.g., User) but have also been augmented with the type of token. 

tkn1 allows all three normal roles.

tkn2 allows all three normal roles.

tkn3 is only for admin.

Here are the example tokens that will be used by the tests.

* 12345 which is of type tkn1 and has role User
* 23456 which is of type tkn1 and has role Super User
* 34567 which is of type tkn1 and has role Admin
* 45678 which is of type tkn2 and has role User
* 56789 which is of type tkn2 and has role Super User
* 67890 which is of type tkn2 and has role Admin
* 78901 which is of type tkn3 and has role Admin

The type of token that application accepts can be set in the application properties (yml) file. Simple set <token type>=true. For example tkn2=true.



Followed the authentication steps in SECURING A REST API WITH SPRING SECURITY (see https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/)

Running the following results in a 401 unauthorized status because there is no token
```
curl localhost:8080/tkn1/user/widgets
```

Running the following results in a 401 unauthorized status because it is an unknown token
```
curl -H "Authorization: Bearer 12345" localhost:8080/tkn1/user/widgets
```

Running the following results in a 200 result status because the token is valid (AuthN)and the user has access to that endpoint (AuthZ)
```
curl -H "Authorization: Tkn1 12345" localhost:8080/tkn1/user/widgets
```

Running the following results in a 403 forbidden status because the token is valid (AuthN) but a super users cannot access that endpoint (AuthZ)
```
curl -H "Authorization: Tkn1 23456" localhost:8080/tkn1/user/widgets
```

Running the following results in a 200 result status because the token is valid (AuthN) and a super user can access the endpoint (AuthZ)
```
curl -H "Authorization: Tkn1 23456" localhost:8080/tkn1/superuser/widgets
```


