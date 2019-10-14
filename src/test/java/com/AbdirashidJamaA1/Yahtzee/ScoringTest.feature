#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

#  @tag1
#  Scenario: Title of your scenario
#    Given I want to write a step with precondition
#    And some other precondition
#    When I complete action
#    And some other action
#    And yet another action
#    Then I validate the outcomes
#    And check more outcomes



Feature: Scoring
  I want to use this template for my feature file
  
  Scenario Outline: test upper score
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score | points  |
      | 1,2,3,4,5 |     1 | 1       |
      | 1,2,3,4,5 |     2 | 2       |


  Scenario Outline: test 3 of a kind
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     10 | 0       |
      | 3,4,3,5,3 |     10 | 18      |
      
      
  Scenario Outline: test 4 of a kind
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,2,5,5 |     11 | 0       |
      | 5,5,2,5,5 |     11 | 18      |
      
  
  Scenario Outline: test full house
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     9  | 0       |
      | 3,3,2,3,2 |     9  | 25      |
  
  Scenario Outline: test Small Sequence
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 2,4,1,5,3 |     8  | 0       |
      | 3,4,1,5,3 |     8  | 30      |
  
  Scenario Outline: test Large Sequence
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     7  | 0       |
      | 4,2,6,5,3 |     7  | 40      |
      
  Scenario Outline: test Yahtzee
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     10 | 0       |
      | 3,4,3,5,3 |     10 | 18      |
  
 