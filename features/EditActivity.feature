Feature: Edit Activity
Description: Allows an activity to be edited by a project leader
Actors: Project leader

  Scenario: Add member to Activity
    Given a project exists
    And signed-in as Project leader
    And there exists an activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    And the activity "Requirements Specification" has no members
    And there is an employee with id "id3"
    When the project leader adds employee with id "id3" to the activity "Requirements Specification"
    Then the activity "Requirements Specification" has 1 member
    And the employee with id "id3" has the activity "Requirements Specification" in their activity list

  Scenario: Fails to Add member to Activity
    Given a project exists
    And signed-in as Project leader
    And there exists an activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    And the activity "Requirements Specification" has no members
    And there is an employee with id "id1"
    When the project leader adds employee with id "id3" to the activity "Requirements Specification"
    Then the error message "No such employee exists" is given

  Scenario: Remove member from Activity
    Given a project exists
    And signed-in as Project leader
    And there is an employee with id "id3"
    And I create a new activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader adds employee with id "id3" to the activity "Requirements Specification"
    And the project leader removes employee with "id3" from the activity
    Then the activity "Requirements Specification" has no members
    And the employee with id "id3" does not have the activity "Requirements Specification" in their activity list

  Scenario: Fails to Remove member from Activity
    Given a project exists
    And signed-in as Project leader
    And there exists an activity with the name "Requirements Specification", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    And the activity "Requirements Specification" has no members
    When the project leader removes employee with "id3" from the activity
    Then the error message "No such employee assigned to activity" is given

  Scenario: Update activity time estimate
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader updates the time estimate of "Design" to budgeted time of 50
    Then the system reflects the new estimate: of 50.0

  Scenario: Update activity time fails
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader updates the time estimate of "Design" to budgeted time of -1
    Then the error message "time out of bounds" is given

  Scenario: Rename an activity
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Programming", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader renames "Programming" to "UI Programming"
    Then the system updates the activity name

  Scenario: Rename to an activity that already exists
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "UI Programming", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    And I create a new activity with the name "Programming", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader renames "Programming" to "UI Programming"
    Then the error message "Activity already exists" is given

  Scenario: Extend an activity's deadline
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "NewYear", start week 7, end week 9, start year 2025, end year 2025, and budgeted time 100
    When the project leader extends the deadline to week 12 of year 2025
    Then the system updates the activity deadline to week 12 of year 2025

  Scenario: Edit an activity that does not exist
    Given a project exists
    And signed-in as Project leader
    And there is no activity named "Research"
    When the project leader attempts to edit "Research"
    Then the error message "Activity not found" is given

  Scenario: Remove Activity
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    And there is an employee with id "id3"
    When the project leader adds employee with id "id3" to the activity "Design"
    And the project leader removes the activity "Design"
    Then the system confirms the activity "Design" has been removed
    And the employee with id "id3" does not have the activity "Requirements Specification" in their activity list

  Scenario: Fails to Remove Activity
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader removes the activity "misspellDisign"
    Then the error message "Activity not found" is given

  Scenario: Activity Calulates Weeks till Completion
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
    When the project leader checks the weeks till completion of "Design"
    Then the system returns the number of weeks till completion: 2 weeks
  # Scenario: Activity Needs Completion
  #   Given a project exists
  #   And signed-in as Project leader
  #   And I create a new activity with the name "Design", start week 12, end week 14, start year 2025, end year 2025, and budgeted time 100
  #   When the project leader checks the weeks till completion of "Design"
  #   Then the system returns the message "Activity needs completion"
  # Scenario: Activity is not in 30 min interval

