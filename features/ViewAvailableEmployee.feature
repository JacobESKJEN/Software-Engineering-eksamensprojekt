Feature: View available employees
Description: Find available employees within certain time-interval
Actors: Employee

  Scenario: Employee with no activities yet
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And the project "Test" has 0 activities between week 12 of year 2025 and week 15 of year 2025
    When a project leader checks for available employees between week 12 of year 2025 and week 15 of year 2025
    Then the employee with id "1" is in the list of available employees

  Scenario: Only 1 employee who is busy
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And the project "Test" has 30 activities between week 17 of year 2025 and week 19 of year 2025
    And the employee with id "1" is assigned to all activies of project "Test"
    When a project leader checks for available employees between week 17 of year 2025 and week 19 of year 2025
    Then the list of available employees is empty

  Scenario: 2 employees, one of which is busy
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And there is an employee with id "2"
    And the project "Test" has 30 activities between week 17 of year 2025 and week 19 of year 2025
    And the employee with id "1" is assigned to all activies of project "Test"
    When a project leader checks for available employees between week 17 of year 2025 and week 19 of year 2025
    Then the employee with id "2" is in the list of available employees
    And the employee with id "1" is not in the list of available employees

  Scenario: Employee with no activities and project leader checks available people for activity
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And the project "Test" has 0 activities between week 17 of year 2025 and week 19 of year 2025
    When a project leader checks for available employees for activity "Test activity" with start week 17, start year 2025, end week 19, end year 2025, and budgeted time 100
    Then the employee with id "1" is in the list of available employees

  Scenario: Employee with one activity and project leader checks available people for same activity
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And the project "Test" has 1 activities between week 17 of year 2025 and week 19 of year 2025
    And the employee with id "1" is assigned to all activies of project "Test"
    When a project leader checks for available employees for activity at index 0 for project "Test"
    Then the list of available employees is empty

  Scenario: 2 employees, one of which is assigned to the activity and project leader checks available people for the same activity
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And there is an employee with id "2"
    And the project "Test" has 1 activities between week 17 of year 2025 and week 19 of year 2025
    And the employee with id "1" is assigned to all activies of project "Test"
    When a project leader checks for available employees for activity at index 0 for project "Test"
    Then the employee with id "2" is in the list of available employees
    And the employee with id "1" is not in the list of available employees
