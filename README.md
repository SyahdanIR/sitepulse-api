# SitePulse

SitePulse is a full-stack website monitoring application that monitors website availability and response performance.

The application allows users to register websites, perform health checks, automatically monitor active websites, store health check history, and view website uptime statistics.

> 🚧 This project is currently under development.

## ✨ Features

- Website management (Create, Read, Update, Delete)
- Website health checking
- Automatic health checks using scheduled tasks
- Health check history
- Website uptime statistics
- Average response time monitoring
- HTTP status code monitoring
- RESTful API
- PostgreSQL database persistence
- Response DTO for clean API responses

## 🛠️ Tech Stack

### Backend

- **Java 21**
- **Quarkus**
- **RESTEasy Reactive**
- **Hibernate ORM with Panache**
- **PostgreSQL**
- **Maven**

### Frontend

- **Vue 3**
- **TypeScript**
- **Pinia**
- **Vue Router**
- **Axios**

> The frontend is planned and currently under development.

## 🏗️ Architecture

SitePulse follows a layered backend architecture:

```text
Resource
   ↓
Service
   ↓
Repository
   ↓
Entity
   ↓
PostgreSQL