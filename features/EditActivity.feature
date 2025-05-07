Feature: Edit Activity
Description: Allows an activity to be edited by a project leader
Actors: Project leader

  Scenario: Add member to Activity
    Given a project exists
    And signed-in as Project leader
    And there exists an activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-04-01", and budgeted time 100
    And the activity "Requirements Specification" has no members
    And there is an employee with id "id3"
    When the project leader adds employee with id "id3" to the activity "Requirements Specification"
    Then the activity "Requirements Specification" has 1 member

  Scenario: Fails to Add member to Activity
    Given a project exists
    And signed-in as Project leader
    And there exists an activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-04-01", and budgeted time 100
    And the activity "Requirements Specification" has no members
    And there is an employee with id "id1"
    When the project leader adds employee with id "id3" to the activity "Requirements Specification"
    Then the error message "No such employee exists" is given

  Scenario: Remove member from Activity
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
    When the project leader adds employee with id "id3" to the activity "Requirements Specification"
    And the project leader removes employee with "id3" from the activity
    Then the activity "Requirements Specification" has no members

  Scenario: Fails to Remove member from Activity
    Given a project exists
    And signed-in as Project leader
    And there exists an activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-04-01", and budgeted time 100
    And the activity "Requirements Specification" has no members
    When the project leader removes employee with "id3" from the activity
    Then the error message "No such employee assigned to activity" is given

  Scenario: Update activity time estimate
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
    When the project leader updates the time estimate of "Design" to budgeted time of 50
    Then the system reflects the new estimate: of 50.0

  Scenario: Update activity time fails
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Design", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
    When the project leader updates the time estimate of "Design" to budgeted time of -1
    Then the error message "time out of bounds" is given

  Scenario: Rename an activity
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "Programming", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
    When the project leader renames "Programming" to "UI Programming"
    Then the system updates the activity name

  Scenario: Extend an activity's deadline
    Given a project exists
    And signed-in as Project leader
    And I create a new activity with the name "NewYear", start date "2025-12-31", end date "2025-12-31", and budgeted time 1
    When the project leader extends the deadline to new "2026-01-01"
    Then the system updates the activity deadline

  Scenario: Edit an activity that does not exist
    Given a project exists
    And signed-in as Project leader
    And there is no activity named "Research"
    When the project leader attempts to edit "Research"
    Then the error message "Activity not found" is given
