# MeroBazar Ecommerce Backend

[![Made with ❤️](https://img.shields.io/badge/Made%20with-❤️-dark?style=flat-square)](https://github.com/YourGitHubUsername)  
![GitHub last commit](https://img.shields.io/github/last-commit/Anush980/meroBazar-backend?style=flat-square)  

The backend for **MeroBazar Ecommerce**, built with **Java Spring Boot**, **Spring Security**, and **PostgreSQL**. This API handles user authentication for now!

---

## 🛠 Technologies Used (Backend)

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=spring&logoColor=white)  
![PostgreSQL](https://img.shields.io/badge/PostgresSQL-4479A1?style=flat-square&logo=postgreSql&logoColor=white)  
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white)  

<!-- ---

### ⚠️ Note
> The frontend for this project is in a **separate repository** (React.js, HTML5, CSS3).  

--- -->

## 📦 Features

- **User Authentication**: Secure login and registration with JWT  
- **User Roles**: Admin and customer roles for access control  
<!-- - **Product Management**: CRUD operations for products and categories  
- **Order Management**: Create, update, and track orders  
- **Inventory Management**: Track stock levels for products   -->
- **Soft Delete**: Users and products can be deactivated instead of permanent deletion  

<!-- ---

## 🌐 Live Demo

[![Live Demo](https://img.shields.io/badge/MeroBazar%20Demo-Click-0D1117?style=for-the-badge&logo=github&logoColor=black)](#)  

*(Link your live deployed backend or Postman collection here if available)*  

--- -->

## 📄 License

This project is open-source under the [MIT License](./LICENSE).  
You can use, modify, and share it with proper credit.

---

## 🚀 Installation (Backend)

1. **Clone the repository**

```bash
git clone https://github.com/Anush980/meroBazar-backend.git
cd merobazar-backend
```
2. **Install dependencies / build project**

```bash
mvn clean install
```
3. **Setup env**

```bash
JWT_SECRET=your_secret_key
JWT_EXPIRATION_MS=expiration_time_in_ms
```
4. **Run code**

```bash
mvn spring-boot:run
```

5. **Api EndPoints**

| Method | Endpoint                   | Description                    |
| ------ | -------------------------- | ------------------------------ |
| GET    | /api/health                | Check health of api            |
| POST   | /api/auth/register         | Register a new user            |
| POST   | /api/auth/login            | Login and receive JWT token    |
| GET    | /api/users                 | Get all users                  |
| GET    | /api/users/{id}            | Get user by ID                 |
| PUT    | /api/users/{id}            | Update user                    |
| DELETE | /api/users/{id}            | Soft delete user               |
---

Built by [Anush980](https://github.com/Anush980) – Feel free to ⭐ the repo or contribute!

