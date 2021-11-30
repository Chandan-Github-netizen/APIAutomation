Feature: Validation of get method

  #npm install -g json-server
  #json-server --watch db.json

  @GetUserDetails
  Scenario Outline: Send a valid Request to get user details

    Given I send a request to the URL to get user details
    Then the response will return status 200 and id <id> and salary <employee_salary> and name "<firstname>" and age <employee_age> and message "<message>"

    Examples:
      |id  |employee_salary|firstname |employee_age  |message                                  |
      |1   |320800         |Chandan   |61            |Successfully! Record has been fetched.   |


  @PatchUser
  Scenario Outline: Send a valid Request to patch user details

    When Client sends a request to the URL to patch user details "<firstname>" and "<lastname>"
    Then the details should be updated successfully "<firstname>" with status code as 200

    Examples:
      |firstname |lastname  |
      |Ben   |Parker   |

  @PutUser
  Scenario Outline: Send a valid Request to put user details

    When Client sends a request to the URL to put user details "<firstname>" "<lastname>" and "<subjectID>"
    Then the details should be replaced successfully "<firstname>" with status code as 200

    Examples:
      |firstname |lastname  |subjectID |
      |Mary   |Jane   | 3 |


  @PostUser
  Scenario Outline: Send a valid Request to post user details

    When Client sends a request to the URL to post user details "<firstname>" "<lastname>" and "<subjectID>"
    Then the details should be posted successfully "<firstname>" with status code as 200

    Examples:
      |firstname |lastname  |subjectID |
      |Mary   |Jane   | 3 |
