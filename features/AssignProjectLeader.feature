Feature: Assign project leader
Description: Assign a project leader to a project
Actors: Employee, Project leader

  Scenario: Assign project leader to a project without a leader
    Given that there exists a project with name "Test"
    And there is an employee with id 1
    When the employee with id 1 assigns the employee with id 1 as project leader of the project "Test"
    Then the project leader of "Test" has id 1

  Scenario: Assign project leader to a project with a leader
    Given that there exists a project with name "Test"
    And there is an employee with id 1
    And there is an employee with id 2
    And the project "Test" has a project leader with id 1
    When the employee with id 2 assigns the employee with id 2 as project leader of the project "Test"
    Then the error message "Project already has a leader" is given

  Scenario: Switch project leader of a project
    Given that there exists a project with name "Test"
    And there is an employee with id 1
    And there is an employee with id 2
    And the project "Test" has a project leader with id 1
    When the employee with id 1 assigns the employee with id 2 as project leader of the project "Test"
    Then the project leader of "Test" has id 2
