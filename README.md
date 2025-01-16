## Overview

This project is a database and application solution developed for the "GameOn" system, designed to manage games, players, and their activities. The project spans two phases, focusing on database development and application implementation.

## Features

### Phase 1: Database Development
1. **Data Modeling:**
   - Designed conceptual and relational data models, normalized to 3NF, including integrity constraints.

2. **Database Implementation:**
   - Scripts to create and remove physical database structures.
   - Initial database population with sample data.

3. **Stored Procedures and Functions:**
   - Manage player accounts: Create, deactivate, and ban players.
   - Compute player and game statistics.
   - Assign badges based on game performance.
   - Manage chats, including creating conversations, adding players, and sending messages.

4. **Views and Triggers:**
   - Generated player statistics and information views.
   - Automated badge assignments and triggers for state changes.

5. **Testing:**
   - Comprehensive test scripts to validate functionality, including success and error scenarios.

### Phase 2: Application Development
1. **Java Application:**
   - Access database functionalities using Java Persistence API (JPA).
   - Implemented transaction management, error handling, and resource cleanup.

2. **Additional Features:**
   - Badge score updates with optimistic and pessimistic locking.
   - Custom error handling for concurrent access conflicts.

3. **Modular Architecture:**
   - Implemented using Maven projects for modularity and dependency management.

## Technology Stack
- **Database:** PostgreSQL with PL/pgSQL for stored procedures.
- **Backend:** Java (JPA implementation for database access).
- **Build Tool:** Maven.
- **Version Control:** Git.

## How to Run
1. **Database Setup:**
   - Run the provided SQL scripts to set up and populate the database.

2. **Java Application:**
   - Import the Maven project into an IDE (e.g., IntelliJ IDEA or Eclipse).
   - Configure the database connection in `persistence.xml`.
   - Run the application to interact with the database.

3. **Testing:**
   - Execute the provided test scripts to verify the functionality.

## File Structure
- `Phase1/`
  - `scripts/`: SQL scripts for database creation, removal, and population.
  - `tests/`: Test scripts for Phase 1 functionalities.
  - `docs/`: Documentation and report for Phase 1.
- `Phase2/`
  - `src/`: Source code for the Java application.
  - `tests/`: Test scripts for Phase 2 functionalities.
  - `docs/`: Documentation and report for Phase 2.

## Authors
Developed by students of the **Licenciatura em Engenharia Informática e de Computadores** under the guidance of **Professors Afonso Remédios, Nuno Leite, and Walter Vieira**.

## Acknowledgments
Special thanks to the "GameOn" team for providing a comprehensive system requirement document that guided this project.
"""

# Save this content into a README.md file
file_path = "/mnt/data/README.md"
with open(file_path, "w") as readme_file:
    readme_file.write(readme_content)

file_path
