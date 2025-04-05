Feature: Create project
Description: An employee can create a project
Actors: Employee

  Scenario: Create a new project
    Given that there exists no project
    And there is an employee with id 1
    When an employee creates a new project with name "Test"
    Then there is a project with name "Test"

  Scenario: Create a project that already exists
    Given that there exists a project with name "Test"
    And there is an employee with id 1
    When an employee creates a new project with name "Test"
    Then the error message "Project already exists" is given
