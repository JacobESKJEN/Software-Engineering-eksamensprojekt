Feature: View available employees
Description: Find available employees within certain time-interval
Actors: Project leader

  Scenario: Employee with no activities yet
    Given that there exists a project with name "Test"
    And there is an employee with id "1"
    And the project "Test" has 0 activities
    When a project leader checks for available employees between week 12 and week 15
    Then the employee with id "1" is in the list of available employees
  #Scenario: Only 1 employee who is busy
  #  Given that there exists a project with name "Test"
  #  And there is an employee with id "1"
  #  And the project "Test" has 30 activities
  #  And the employee with id "1" is assigned to all activies of project "Test"
  #  When a project leader checks for available employees between week 17 and week 19
  #  Then the list of available employees is empty
