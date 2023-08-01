@mysql
Feature: MySQL Test
  Scenario: Retrieve Users from MySQL Database
    Given I connect to the MySQL database
    When I execute the SQL query "SELECT * FROM users;"
    Then I should get the following users:
      | name  | age |
      | John  | 30  |
      | Alice | 25  |
      | Bob   | 35  |
