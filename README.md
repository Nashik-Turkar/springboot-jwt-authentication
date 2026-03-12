JWT Security Implementation (Spring Boot) :

A secure authentication and authorization system built using Spring Boot, Spring Security, and JSON Web Token (JWT).
This project demonstrates a production-style JWT security architecture with access tokens, refresh tokens, database token tracking, and role-based API security.

Features:
  Authentication:
    User login using username and password
    Password verification via Spring Security
    JWT Access Token generation
    Refresh Token generation
  
  Authorization:
    Role-Based Access Control (RBAC)
    Secure API access based on user roles
    Method-level authorization support
    
  Token Management:
    Access Token expiration
    Refresh Token mechanism
    Token revocation support
    Token stored in database for session tracking

  Security:
    Stateless authentication
    Secure JWT signing
    Custom authentication filter
    Global exception handling
    
====================================================================
Project Structure:

  com.example.jwtsecurity
  │
  ├── JwtSecurityApplication.java
  │
  ├── config
  │   └── SecurityConfig.java
  │
  ├── controller
  │   ├── AuthController.java
  │   └── UserController.java
  │
  ├── service
  │   ├── UserService.java
  │   ├── TokenService.java
  │   └── AuthService.java
  │
  ├── repository
  │   ├── UserRepository.java
  │   └── TokenRepository.java
  │
  ├── entity
  │   ├── User.java
  │   ├── Role.java
  │   └── Token.java
  │
  ├── security
  │   ├── JwtAuthenticationFilter.java
  │   └── CustomUserDetailsService.java
  │
  ├── util
  │   └── JwtUtil.java
  │
  └── exception
      └── GlobalExceptionHandler.java
====================================================================
Technologies Used
Technology	      Purpose
Java	             Backend development
Spring Boot	       Application framework
Spring Security	   Authentication & authorization
Spring Data JPA	   ORM & database operations
JWT	               Stateless authentication
MySQL	             Database
Maven	             Dependency management

====================================================================
Authentication Flow :
  Client
     │
     │ Login Request
     ▼
  AuthController
     │
     ▼
  AuthenticationManager
     │
     ▼
  CustomUserDetailsService
     │
     ▼
  JWT Token Generated
     │
     ▼
  Token Saved in Database
     │
     ▼
  Client receives Access Token & Refresh Token


Request Flow (Protected APIs) :
  Client Request :
  Authorization: Bearer <AccessToken>
          │
          ▼
  JwtAuthenticationFilter
          │
          ▼
  Validate Token Signature
          │
          ▼
  Check Token Expiry
          │
          ▼
  Check Token Revocation
          │
          ▼
  Set Security Context
          │
          ▼
  Access Granted

===================================================================

Author
Nashik Turkar
Backend Developer
Java | Spring Boot | Microservices | REST APIs
