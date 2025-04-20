Feature: Log in employee
    Description: Let an employee log in as the active user
    Actors: Employee

  Scenario: Valid log in for employee
    Given there is an employee with id "1" and password "password"
    When the user logs in with id "1" and password "password"
    Then the current logged in user has id "1"

  Scenario: Invalid password for log in
    Given there is an employee with id "1" and password "password"
    When the user logs in with id "1" and password "qwerty"
    Then the error message "Invalid login" is given

  Scenario: Invalid id for log in
    Given there is an employee with id "1" and password "password"
    When the user logs in with id "2" and password "password"
    Then the error message "Invalid login" is given
