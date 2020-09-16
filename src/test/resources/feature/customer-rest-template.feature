@Customer @RestTemplate
Feature: Customer scenarios using Spring's RestTemplate

  Scenario: Get all customers with RestTemplate
    Given a request to retrieve all customers is executed
    Then all available customers are returned