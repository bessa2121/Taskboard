# Project Overview

## Project Architecture
This project follows a layered architecture consisting of the following layers:
- Presentation Layer: Handles user interaction.
- Application Layer: Contains business logic and application flow.
- Domain Layer: Contains entities and domain logic.
- Data Layer: Manages data access and persistence.

## Domain Model
- Entities: 
  - Task: Represents a task item with attributes such as title, description, status, and priority.
  - User: Represents a user who can create and manage tasks.

## Business Rules
- A user can create, update, and delete tasks.
- Tasks must have a title and can optionally have a description.
- Each task should be assigned a priority and status.

## Flow Diagrams
- **User Task Flow**: Shows how users interact with the system to create and manage tasks.
- **Data Flow**: Demonstrates how data moves through the different layers of the system.

## Technologies
- Frontend: React.js
- Backend: Node.js and Express
- Database: MongoDB
- Others: Mermaid.js for diagram generation

## Execution Instructions
1. Clone the repository.
2. Install dependencies using `npm install`.
3. Start the server with `npm start`.

## Package Structure
```
/Taskboard
|-- /frontend
|   |-- /src
|-- /backend
|   |-- /models
|   |-- /routes
|-- README.md
```  

## Architectural Decisions
- Chose MERN stack for its flexibility and scalability.
- Used modular design for better maintainability.

## Future Roadmap
- Implement user authentication.
- Optimize performance.
- Add default templates for tasks.

## Technical Improvements
- Refactor codebase for better readability.
- Increase test coverage.

## Manual Tests
- Tested on Chrome, Firefox, and Safari.
- All features and functions have been validated manually.

## Author Information
- Name: [Your Name]
- Contact: [Your Email]

## Current Status
As of **2026-02-16**, the project is functional and in active development.