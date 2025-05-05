Feature: Create Activity  
Description: Create an activity in a project and plan the work for employees 
Actors: Project manager or employee

  Scenario: Successfully create a new activity with valid details  
    Given a project exists  
    When I create a new activity with the name "Requirements Specification", start date "2025-03-17", end date "2025-03-31", and budgeted time 100  
    Then the activity is created and added to the project  

  Scenario: Attempt to create an activity with an invalid date range  
    Given a project exists  
    When I create a new activity with the name "Requirements Specification", start date "2025-04-01", end date "2025-03-31", and budgeted time 100  
    Then the error message "End date must be after start date" is given

  Scenario: Find activity
    Given a project exists
    And there exists an activity with the name "Requirements Specification", start date "2025-03-17", end date "2025-03-31", and budgeted time 100  
    When I try to find an activity with the name "Requirements Specification"
    Then the activity has name "Requirements Specification"