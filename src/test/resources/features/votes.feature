Feature: Votes

  Scenario: GET votes
    Given a base URL "https://api.thecatapi.com/v1" with header key "x-api-key" and header value "DEMO-API-KEY"
    When I send GET request with endpoint "/votes"
    Then I have to store response Body and verify response result is more than 0
    And I should verify status code is 200

    When I select a random voteID from stored votes
    Then I send GET request with randomID to "/votes/{id}"
    And I should verify status code is 200
    And Verify response body not empty and  response should match the corresponding given object

  Scenario: POST vote
    Given a base URL "https://api.thecatapi.com/v1" with header key "x-api-key" and header value "DEMO-API-KEY"
    When I create a new vote using POST request to "/votes"
    Then The response status code should be 201
    And The response match expected value message "SUCCESS"

#  Scenario: GET New ID
#    When I send GET request with new id to "/votes/{id}"
#    Then I should verify status code is 200
#    And the id response should match id request
#
#  Scenario: DELETE created ID
#    When I send DELETE request with same id to "/votes/{id}"
#    Then The response status code should be 200
#    And The response body should match the expected value message "SUCCESS"
#
#  Scenario: Verify Deleted ID
#    When I send a GET request to "/votes/{id}"
#    Then The response status code should be 404
#    And The response should match the expected value message body "NOT_FOUND"

