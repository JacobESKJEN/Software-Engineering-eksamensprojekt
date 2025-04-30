Feature: Edit Activity
Description: Allows an activity to be edited by a project leader
Actors: Project leader
Scenario: Add member to Activity
    # Given signed-in as Project leader
    Given a project exists
    And I create a new activity with the namee "Requirements Specification", start date "2025-04-01", end date "2025-03-31", and budgeted time 100  
    And the activity "Requirements Specification" has 0 members
    When the project leader adds employee with "id3" to the activity
    Then the activity "Requirements Specification" has 1 member 
# Scenario: Update activity time estimate
#     Given signed-in as Project leader
#     And there is a project named "Test"
#     And the project "Test" has an activity "Design" with an estimated time of a
#     When the project leader updates the time estimate of "Design" to amount of
#     Then the system reflects the new estimate:
#     - Activity: Design
#     - Estimated time: total hours
# Scenario: Rename an activity
    # Given signed-in as Project leader
    # And there is a project named "Test"
    # And the project "Test" has an activity named "Design"
    # When the project leader renames "Design" to "UI Design"
    # Then the system updates the activity name:
    # - Old name: Design
    # - New name: UI Design
# Scenario: Extend an activity's deadline
    # Given signed-in as Project leader
    # And there is a project named "Test"
    # And "Test" has an activity "Development" with a deadline of current "YEAR-M
    # When the project leader extends the deadline to new "YEAR-MONTH-DAY"
    # Then the system updates the activity deadline:
    # - Activity: Development
    # - New deadline: "YEAR-MONTH-DAY"
# Scenario: Edit an activity that does not exist
    # Given signed-in as Project leader
    # And there is a project named "Test"
    # And there is no activity named "Research"
    # When the project leader attempts to edit "Research"
    # Then the system displays an error:
    # - "Activity not found"