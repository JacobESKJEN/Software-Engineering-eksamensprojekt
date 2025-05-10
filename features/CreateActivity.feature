Feature: Create Activity
Description: Create an activity in a project and plan the work for employees 
Actors: Project manager or employee

  Scenario: Successfully create a new activity with valid details
    Given a project exists
    When I create a new activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    Then the activity is created and added to the project

  Scenario: Attempt to create an activity with an invalid date range (by week)
    Given a project exists
    When I create a new activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2024, and budgeted time 100
    Then the error message "End date must be after start date" is given

  Scenario: Attempt to create an activity with an invalid date range (by year)
    Given a project exists
    When I create a new activity with the name "Requirements Specification", start week 12, end week 10, start year 2025, end year 2025, and budgeted time 100
    Then the error message "End date must be after start date" is given

  Scenario: Find activity
    Given a project exists
    And there exists an activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When I try to find an activity with the name "Requirements Specification"
    Then the activity has name "Requirements Specification"
