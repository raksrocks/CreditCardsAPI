#Author: Rakesh S

@TestPostEndpoint
Feature: Creates a Credit card with /add endpoint
  Create a credit card with given details

  @CreateWithDataTable
  Scenario: Create a credit card
    Given API is ready
    When  I send a POST request to API with name is <name> and number as <number> and limit as <limit>
    Then verify the Status as <status>

 Examples: 
 
 			| name | number             | limit | status |
      | John | 5364437491157791   | 100.0 | success |
      | Jane | 2221003061364579   | 200.0 | success |
      | Joe  | 2221004673154317   | 300.1 | success |
      | Jim  | 2221004673154328   | 98.94 | error  |
      | Jerry| 2221004673154339   |   0.0 | error  |     