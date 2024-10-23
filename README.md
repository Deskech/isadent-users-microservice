# User Authentication Microservice

This microservice handles user registration and login functionality for the Isadent Software. It is responsible for:
- **User Registration:** Storing new users in the system with necessary credentials and profile information.

- **User Login:** Authenticating users and providing access tokens for secure communication.

__Note: this microservice is still under development__

## Table of Contents
- [Installation](#installation)
- [JavaDocs](#Documentation)
- [Examples](#Examples)
- - [Creating a new user](#creating-a-new-user-)
- - [User Login Request](#user-login-request-)
- [License](#license)
-
## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Deskech/isadent-users-microservice
## Prerequisites
- **Java 17**
- **Maven**

## Documentation
Java docs comming soon...


## Examples

### Creating a New User: ###

```http
POST /users/register

Content-Type: application/json
```
```json
{
   "email": "user@example.com",
   "username": "pinto",
   "password": "securePassword123"

}
```

### User Login Request ###

```http
POST /users/login

Content-Type: application/json
```
```json
{
   "email": "user@example.com",
   "username": "pinto",
   "password": "securePassword123"

}

```

__Response :__
 ```json
{
   "authToken": "validTokenExampleHeader.ValidTokenExamplePayload.ValidTokenExampleSing",
   "validatedUser": {
      "email": "user@example.com",
      "username": "pinto"
   }
}
```
## License
Comming soon...