Feature: Edit Activity
Description: Allows an activity to be edited by a project leader
Actors: Project leader
Scenario: Add member to Activity
    Given signed-in as Project leader
    Given a project exists
    And I create a new activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-04-01", and budgeted time 100  
    And the activity "Requirements Specification" has 0 members
    When the project leader adds employee with "id3" to the activity
    Then the activity "Requirements Specification" has 1 member 
Scenario: Error: Fails to Add member to Activity
    Given signed-in as Project leader
    Given a project exists
    And I create a new activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-04-31", and budgeted time 100
    When the project leader adds employee with "id3" to the activity but the employee does not exist
    Then I receive an error message "No such employee exists"
Scenario: Remove member from Activity
    Given signed-in as Project leader
    Given a project exists
    And I create a new activity with the name "Requirements Specification", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
    And the activity "Requirements Specification" has 1 member 
    When the project leader removes employee with "id3" from the activity
    Then the activity "Requirements Specification" has 0 members
# Scenario: Error: Fails to Remove member from Activity
#     # Given signed-in as Project leader
#     Given a project exists
#     And I create a new activity with the namee "Requirements Specification", start date "2025-04-01", end date "2025-03-31", and budgeted time 100
#     And the activity has 1 member 
#     When the project leader removes employee with "id3" from the activity
#     Then the activity "Requirements Specification" has 0 members
# Scenario: Update activity time estimate
#     Given signed-in as Project leader
#     Given a project exists
#     And I create a new activity with the namee "Design", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
#     When the project leader updates the time estimate of "Design" to budgeted time of 50
#     Then the system reflects the new estimate: of '50'
# # Activity: Design
# # Estimated time: total hours
# Scenario: Rename an activity
#   Given signed-in as Project leader
#     Given a project exists
#     And I create a new activity with the namee "Programming", start date "2025-03-01", end date "2025-03-31", and budgeted time 100
#    When the project leader renames "Programming" to "UI Programming"
#    Then the system updates the activity name:
# # Old name: Design
# # New name: UI Design
# Scenario: Extend an activity's deadline
#   Given signed-in as Project leader
#    Given a project exists
#    And I create a new activity with the namee "NewYear", start date "2025-12-31", end date "2025-12-31", and budgeted time 1
#    When the project leader extends the deadline to new "2026-01-01"
#    Then the system updates the activity deadline:
# # Activity: Development
# # New deadline: "2026-01-01"
# Scenario: Edit an activity that does not exist
#   Given signed-in as Project leader
#    Given a project exists
#    And there is no activity named "Research"
#    When the project leader attempts to edit "Research"
#    Then the system displays an error:
# # "Activity not found"