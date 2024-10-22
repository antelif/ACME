# ACME API

**Swagger**: Visit `http://localhost:8080/acme` when running locally.

## Technologies

___

- **Java 17**
- **Spring Boot**:
    - Version: `3.3.4`
- **Liquibase**:
    - Version: defined by parent
    - There was no use case for using liquibase. It just seems fun to me and I wanted to play around
      with json because I have only used it with XML so far.
- **PostgreSQL**
    - Version: defined by parent
- **Test Containers**:
    - Version: Defined by parent

## Operations
___

Users can:
1. Create meeting rooms
2. Create bookings for meeting rooms
3. Cancel bookings 
   - This uses `DELETE` HTTP method, so it's irreversible
4. View booking for a given meeting room.
