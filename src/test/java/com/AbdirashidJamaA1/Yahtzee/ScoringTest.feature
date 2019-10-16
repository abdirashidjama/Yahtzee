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
      #ones
      | 2,2,3,4,5 |     1 | 0       |
      | 1,2,3,4,5 |     1 | 1       |
      #twos
      | 1,4,3,4,5 |     2 | 0       |
      | 1,2,3,4,5 |     2 | 2       |
      #threes
      | 1,4,1,4,5 |     3 | 0       |
      | 1,2,3,4,5 |     3 | 3       |
      #fours
      | 1,2,1,2,5 |     4 | 0       |
      | 1,2,3,4,5 |     4 | 4       |
      #five
      | 1,4,1,4,1 |     5 | 0       |
      | 1,2,3,4,5 |     5 | 5       |
      #six
      | 1,4,1,4,5 |     6 | 0       |
      | 1,2,6,4,5 |     6 | 6       |


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
      | 5,5,2,5,5 |     11 | 22      |
      
  
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
      | 3,4,1,5,3 |     8  | 0       |
      | 2,4,1,1,3 |     8  | 30      |
  
  Scenario Outline: test Large Sequence
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     7  | 0       |
      | 4,2,6,5,3 |     7  | 40      |
      
  Scenario Outline: test first Yahtzee
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     13 | 0       |
      | 5,5,5,5,5 |     13 | 50      |
      
  Scenario Outline: test chance
    Given I recieve these <dice>
    When I choose category <score> to score in
    Then I get <points>

    Examples: 
      | dice      | score  | points  |
      | 3,4,1,5,3 |     12 | 16      |
      | 1,2,3,4,5 |     12 | 15      |
  
  Scenario: Upper Section Bonus
    Given I have upper section scores totalling fifty eight
    And a hand containing containg eight points worth of two's 
    When I score the hands as upperScore two
    Then I should have an upper bonus score of 35
    And my total points should be 101
  
  Scenario: Upper Section Bonus Zero
    Given I have upper section scores totalling twenty five
    And a hand containing containg eight points worth of two's 
    When I score the hands as upperScore two
    Then I should have an upper bonus score of 0
    And my total points should be 33
  
  
  Scenario: Yahtzee Bonus Zero
    Given I have already scored a Yahtzee and have fifty points
    And a hand containing a Yahtzee 
    When I score the Yahtzee
    Then I should have Yahtzee bonus and a total score 150 points

  Scenario Outline: Score no Reroll
    Given I recieve dice
    When I roll dice 
    And player choose <cat> to score
    Then player get points for <cat> in scoresheet
    
    Examples: 
        | cat     |
        | 1       |
        | 2       |
        | 3       |
        | 4       |
        | 5       |
        | 6       |
        | 7       |
        | 8       |
        | 8       |
        | 10      |
        | 11      |
        | 12      |
        | 13      |
  
  
  
  
  
 