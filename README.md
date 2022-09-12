# Project 1 - Employee Reimbursment System (ERS)

## What's currently complete:
 - GET & POST methods functional within UserServlet & ReimbursementServlet
 - From the given UI skeleton, adapted to my project specifics and was able to get REGISTER & LOGIN pages to work and persist new registered users onto database tables
 - Through Postman, I could create reimbursements given username & amount plus default pending status and null resolver as well as get list of reimbursements by status
 - DAO: Reimbursements can be Created, Read (by ID, Username & Status) & Updated... No Delete
 - DAO: Users can be created, Read( by ID and Username)... No Update or Delete


## Executive Summary
The Expense Reimbursement System (ERS) manages the process of reimbursing employees for expenses incurred while on company time, such as travel, food and lodging. All employees in the company can login and submit requests for reimbursement, as well as view their past cikets and pending requests. Finance managers (admins) can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

# Tech Stack
 - Java 8, Servlets, JDBC
 - Apache Maven, Tomcat
 - PostgreSQL, AWS RDS
 - HTML5/CSS/JS
 - AJAX / Fetch API

The persistence-layer system shall use JDBC to connect to a Postgres database. The API-layer shall utilize Java servlets to expose a public interface. The front-end view shall use HTML/CSS/JavaScript to make an application that can call server-side components in a generally RESTful manner. The middle tier shall follow proper layered architecture, and have reasonable JUnit test coverage of the service layer. Webpages shall be styled to be functional and readable.

# Functional Requirements
 - Domain objects persisted in relational database
 - Database should be in 3NF
 - CRUD functionality for all domain objects
 - All CRUD functionality accessible via RESTful API
 - Functional web UI to consume RESTful API
 - Workflows to complete all user stories
 - Validate all user input
 - Unit test coverage for service-layer classes 

# User Stories
### Requirements:
#### Guest:
 - As a guest, I can register for a new account
 - As a guest, I can log into my account

#### User:
 - As a user, I can submit a request for reimbursement
 - As a user, I can cancel a pending request for reimbursement
 - As a user, I can view my pending and completed past requests for reimbursement
 - As a user, I can edit my pending requests for reimbursement

#### Admin/Finance Manager:
 - As an admin, I can approve expense reimbursements
 - As an admin, I can deny expense reimbursements
 - As an admin, I can filter requests by status

#### Stretch Goals:
 - As an admin, I can change a user's role between admin and user

**State-chart Diagram (Reimbursement Statuses)** 

![](./imgs/state-chart.jpg)

**Logical Model**

![](./imgs/logical.jpg)

**Physical Model**

![](./imgs/physical.jpg)

**Use Case Diagram**

![](./imgs/use-case.jpg)

**Activity Diagram**

![](./imgs/activity.jpg)
