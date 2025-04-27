Feature: Edit Activity
Description: Allows an activity to be edited by a project leader
Actors: Project leader


  # Scenario: Add member to Activity
  #     #Given signed-in as Project leader
  #     Given there is a project named "Test"
  #     And the project "Test" has 2 members
  #     When the project leader adds employee with id 3 to the activity
  #     Then the activity has 3 members
#rewrote the test to fit better
 

  # Scenario: Update activity time estimate
  #     Given signed-in as Project leader
  #     And there is a project named "Test"
  #     And the project "Test" has an activity "Design" with an estimated time of a
  #     When the project leader updates the time estimate of "Design" to amount of
  #     Then the system reflects the new estimate:
  #     # - Activity: Design
  #     # - Estimated time: total hours

  # Scenario: Rename an activity
  #     Given signed-in as Project leader
  #     And there is a project named "Test"
  #     And the project "Test" has an activity named "Design"
  #     When the project leader renames "Design" to "UI Design"
  #     Then the system updates the activity name:
  #     # - Old name: Design
  #     # - New name: UI Design

  # Scenario: Extend an activity's deadline
  #     Given signed-in as Project leader
  #     And there is a project named "Test"
  #     And "Test" has an activity "Development" with a deadline of current "YEAR-M
  #     When the project leader extends the deadline to new "YEAR-MONTH-DAY"
  #     Then the system updates the activity deadline:
  #     # - Activity: Development
  #     # - New deadline: "YEAR-MONTH-DAY"

  # Scenario: Edit an activity that does not exist
  #     Given signed-in as Project leader
  #     And there is a project named "Test"
  #     And there is no activity named "Research"
  #     When the project leader attempts to edit "Research"
  #     Then the system displays an error:
  #     # - "Activity not found"