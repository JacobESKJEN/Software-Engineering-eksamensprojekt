Feature: Special Activity Management
  As a project leader
  I want to manage special activities
  So that I can assign specific tasks or events to employees with defined start and end dates

  Scenario: Create a valid special activity
    Given a project exists
    And there is an employee with id "1"
    When the project leader creates a special activity named "Vacation" with start date "2025-05-01" and end date "2025-05-07" for employee "1"
    Then the activity "Vacation" is created and added to the project
    And the special activity "Vacation" has start date "2025-05-01" and end date "2025-05-07"

  Scenario: Fail to create a special activity with invalid dates
    Given a project exists
    And there is an employee with id "1"
    When the project leader creates a special activity named "Invalid Activity" with start date "2025-05-10" and end date "2025-05-01" for employee "1"
    Then the error message "End date must be after start date" is given

  Scenario: Fail to create a special activity for a non-existent employee
    Given a project exists
    When the project leader creates a special activity named "Vacation" with start date "2025-06-01" and end date "2025-06-07" for employee "2"
    Then the error message "Unable to find employee" is given

  Scenario: Retrieve details of a special activity
    Given a project exists
    And there is an employee with id "1"
    And the project leader creates a special activity named "Course" with start date "2025-07-01" and end date "2025-07-03" for employee "1"
    When the project leader retrieves the details of the special activity "Course"
    Then the special activity "Course" has start date "2025-07-01" and end date "2025-07-03"
    And the special activity is assigned to employee "1"

  Scenario: Check if an activity is special
    Given a project exists
    And there exists a special activity with the name "Special Meeting", start date "2025-05-01", and end date "2025-05-07"
    When I check if the activity "Special Meeting" is special
    Then the result should be true
