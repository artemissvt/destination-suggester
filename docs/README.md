
# Destination Suggester Web Application

## Overview
Destination Suggester is a full-stack web application that allows users to 
generate personalized travel destination recommendations using natural language input.
The application integrates a Spring Boot backend, a FastAPI-based machine learning service, 
and a responsive frontend built with HTML, CSS, and JavaScript.

Users can:
- Create accounts
- Authenticate securely
- Generate AI-powered destination recommendations
- View recommendation history
- Persist recommendation data in a relational database

## Features

### User Authentication
- User registration
- Login functionality
- BCrypt password hashing
- Session-based authentication

### AI Recommendation Integration
- Sends user prompts to an external AI service
- Receives destination recommendations
- Displays results dynamically

### Recommendation History
- Stores recommendation responses
- Associates recommendations with users
- Allows users to revisit previous searches

### Session Management
- Session validation
- Protected pages
- Automatic redirects for unauthenticated users

## System Architecture
```text
Browser
   |
   v
Spring Boot Application
   |
   +---- MySQL Database
   |
   +---- FastAPI Recommendation Service
```
## Technology Stack

### Backend
- Java 17
- Spring Boot
- Spring MVC
- JDBC
- BCrypt

### Frontend
- HTML5
- CSS3
- JavaScript
- Fetch API

### Database
- MySQL

### External Services
- FastAPI Recommendation API
- DigitalOcean App Platform

## Deployment
Production endpoint: https://destination-suggester-b9aov.ondigitalocean.app/
