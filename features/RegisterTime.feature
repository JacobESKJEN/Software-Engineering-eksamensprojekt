Feature: Register Time
Description: Employee are able to register time to an activity
Actors: Employee

  Scenario: Employee registers valid hours for an activity
    Given a project has been created
    Given there exists an activity with the name "Design", start date "2025-05-11", end date "2025-05-12", and budgeted time 100
    Given there exists an employee with id "ID03"
    When the employee registers 2.5 hours to the activity "Design"
    Then the activity's total logged hours is 2.5
    Then the employee has logged 2.5 hours to the activity


  Scenario: Employee registers invalid time (not a multiple of 0.5)
    Given a project has been created
    Given there exists an activity with the name "Design", start date "2025-05-11", end date "2025-05-12", and budgeted time 100
    Given there exists an employee with id "ID03"
    When the employee registers 0.3 hours to the activity "Design"
    Then the error message "Time is given in half hour intervals" is given



