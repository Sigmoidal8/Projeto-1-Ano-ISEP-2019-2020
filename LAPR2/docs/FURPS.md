# Supplementary specification

## Functionalities

**Safety:**

- **The interactions of the users (i.e. Admin, Freelancer, Manager, Collaborator) must be preceded by an authentication process**
- **The use of the platform by other people is restricted to the registration of organizations.**



## Usability

**Aesthetics:**

- **In visual terms, the application's graphical interface must be based on a color palette structured in two colors (primary and secondary).**


## Reliability / Reliability

**possibility of recovery:**
- **The system informs the user if an error occurs.**


## Performance
- n\a


## Supportability

**Configurability:**

- **External algorithm (password generator) configured only when implementing the system.**
- **External algorithm (currency converter) configured only when implementing the system.**

**Testability:**

- **The Aplication contains unit test to all calculating methods.**



## +

### Design restrictions

- **Adopt good practices for identifying requirements and analyzing and designing OO software**

- **Reuse the existing user management component at T4J**

- **Passwords must be generated  using an external algorithm (i.e. designed by third parties) and configured only when deploying
   of the system.**

- **The user interface should be aplied using JavaFx**


### Implementation restrictions

- **Implement the core software core in Java**

- **Adopt recognized coding standards**


### Restrições de interface

- **Passwords must be generated using an external algorithm (i.e. designed by a third party).**

- **The amount sent to the freelancer must be converted using an external algorithm.**

### Restrições físicas

- n\a
