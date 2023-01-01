@api.profilemanagement
Feature: Profile Management

  Scenario: add new members, verify all members were added successfully, verify members info is correct
    When adding 3 new random members to Profile Manager
    Then new members should be displayed in Home Page
    And new members should be queryable in Search Member Page
    And verify information of new members is correct in View Member Page