# Spring Security Access and Refresh JWTs

## User Model
![User Model](https://user-images.githubusercontent.com/29623199/127276568-62f9fcbf-7ee3-4140-9736-32ac96b909a9.JPG)

## Authentication and Authorization
![Authentication vs Authorization](https://user-images.githubusercontent.com/29623199/127310101-8fdb801b-dc38-4cb6-857c-c7ed9a647a06.JPG)

## JSON Web Token (JWT) Workflow
![jwt work flow](https://user-images.githubusercontent.com/29623199/127441861-78405308-0bcd-48b3-8e21-c0f64490dbcc.JPG)

1) Client sends Credentials to Application
2) If the Credentials are correct the application issues a JWT for the Client
3) On the next Request the Client passes the JWT to the Application
4) The Application checks the Validation of the JWT and the Permissions for this Client from the JWT