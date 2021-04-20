@Customer @RestAssured
Feature: Customer scenarios using RestAssured

  Scenario: Get all customers with RestAssured
    Given the customer endpoint exists
    When a valid request is sent to retrieve all customers
    Then the response status code should be 200
    And the response should have a length of 1

  Scenario: Get customer with RestAssured
    Given the customer endpoint exists
    When a valid request is sent to retrieve a customer with id 'a26dfab2-79fa-4ef4-b3e3-88c0b0c19e1f'
    Then the response status code should be 200
    And the customer should have a name of 'Bruce Wayne'
