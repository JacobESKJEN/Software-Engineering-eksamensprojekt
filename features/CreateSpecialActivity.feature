# Feature: Create Special Activity
# Description: Create an Special activity in a project and plan the work for employees 
# Actors: Project manager or employee

#   Scenario: Successfully create a new Special activity with valid details
#     Given a project exists
#     When I create a new Special activity with the name "Course", start date "2025-03-01", end date "2025-03-31", and "huba" as the employee
#     Then the activity is created and added to the project

#     Scenario: Rename an Special activity
#     Given a project exists
#     And signed-in as Project leader
#     and I create a new Special activity with the name "Vacation", start date "2025-03-01", end date "2025-03-31", and "huba" as the employee
#     When the project leader renames "Vacation (huba)" to "New Year"
#     Then the system updates the activity name

#     Scenario: Extend an Special activity's deadline
#     Given a project exists
#     And I create a new Special activity with the name "Vacation", start date "2025-12-31", end date "2025-12-31",  and "huba" as the employee
#     When I extend the deadline to "2026-01-01"
#     Then the system updates the activity deadline

#   Scenario: Attempt to create an Special activity with an invalid date range
#     Given a project exists
#     When I create a new Special activity with the name "Course", start date "2025-03-31", end date "2025-03-01", and "huba" as the employee
#     Then the error message "End date must be after start date" is given


