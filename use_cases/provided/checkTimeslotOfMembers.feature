Feature: check timeslot of Members
Description: A project leader is able to veiw time slot of employees
Actors: Employee, Project leader

  Scenario: a Project leader checks time-slot of a list of Employee
    Given signed-in as Project leader
    And there is an employee with id 1 and timeslot "empty" in project "test"
    And there is an employee with id 2 and timeslot "full" in project "test"
    When the project leader view's the list of employee timeslot's
    Then employee id 1 and 2 are givenand employee 1 and 2's timeslots are given

  Scenario: a Project leader checks if any employee's have empty time-slot
    Given signed-in as Project leader
    And there is an employee with id 1 and timeslot "empty" in project "test"
    And there is an employee with id 2 and timeslot "full" in project "test"
    When the project leader view's the list of not busy employee's
    Then employee id 1 is given
