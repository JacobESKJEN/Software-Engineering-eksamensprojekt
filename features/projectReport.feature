Feature: Project Status

  Scenario: Project leader views time usage per activity
    Given signed-in as Project leader with id "emp1"
    Given there exists a project with name "Test"
    Given the project "Test" has activities "Design" and "Development"
    Given employee "2" is added to the activity "Design"
    Given employee "3" is added to the activity "Development"
    Given 5.0 hours are logged by "2" on "Design"
    Given 8.0 hours are logged by "3" on "Development"
    When the project leader generates a project report
    Then the report contains "Design: 5.0 hours"
    Then the report contains "Development: 8.0 hours"
    Then the report contains "Total hours logged for the project 13.0"

  Scenario: Project leader checks expected remaining work
    Given signed-in as Project leader with id "emp1"
    Given there exists a project with name "Test"
    Given the project "Test" has activities "Design" and "Development"
    Given employee "2" is added to the activity "Design"
    Given employee "3" is added to the activity "Development"
    Given 20.0 hours are logged by "2" on "Design"
    Given 20.0 hours are logged by "3" on "Development"
    When the project leader generates a project report
    Then the report contains "Design: 20.0 hours logged"
    Then the report contains "Development: 20.0 hours logged"
    Then the report contains "Expected total hours left: -20.0"

  Scenario: Project leader generates a full project report
    Given signed-in as Project leader with id "emp1"
    Given there exists a project with name "Test"
    Given the project "Test" has activities "Design" and "Development"
    Given employee "2" is added to the activity "Design"
    Given employee "3" is added to the activity "Development"
    Given 5.0 hours are logged by "2" on "Design"
    Given 8.0 hours are logged by "3" on "Development"
    When the project leader generates a project report
    Then the report contains "ID: 2"
    Then the report contains "Design: 5.0 hours"
    Then the report contains "ID: 3"
    Then the report contains "Development: 8.0 hours"
    Then the report contains "Total hours logged for the project 13.0"
