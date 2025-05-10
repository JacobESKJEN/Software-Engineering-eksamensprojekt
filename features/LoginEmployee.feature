Feature: Log in employee
    Description: Let an employee log in as the active user
    Actors: Employee

  Scenario: Valid log in for employee
    Given there is an employee with id "1"
    When the user logs in with id "1"
    Then the current logged in user has id "1"

  Scenario: Invalid id for log in
    Given there is an employee with id "1"
    When the user logs in with id "2"
    Then the error message "Invalid login" is given

  Scenario: Log out
    Given there is an employee with id "1"
    And the user logged in with id "1"
    When the user logs out
    Then there is no current logged in user

  Scenario: Log out without having logged in
    Given there is an employee with id "1"
    When the user logs out
    Then the error message "Not possible to log out" is given

  Scenario: Logged in employee adds employee to list of employees
    Given there is an employee with id "1"
    And the user logged in with id "1"
    When the user adds employee with id "2"
    Then the employee with id "2" is in the list of employees
