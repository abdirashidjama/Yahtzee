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
@tag
Feature: rerolls and Scorring
	I want to use this template for my feature file
    Scenario Outline: test one reroll and score dice
      Given player recieves dice
      When player chooses to reroll all the dice
      And player choose <category> to score in
      Then player get points for that <category>
    
    
      Examples: 
        | category|
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
        
        
    Scenario Outline: test two rerolls and score dice
      Given player recieves dice
      When player chooses to reroll all the dice
      And  player chooses to reroll all the dice
      And player choose <category> to score in
      Then player get points for that <category>
      
      Examples: 
        | category|
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
        
        
    Scenario Outline: test reroll less than five dice once
      Given player recieves dice and <holds> a couple
      When player rolls the other dice that are not among the <holds>
      And player choose <category> to score in
      Then player get points for that <category>
      
      Examples: 
        | holds | category|
        |  1    | 1       |
        |  1,2  | 2       |
        |1,2,3  | 3       |
        #|1,2,3,4| 4       |
        #|2,3,4,5| 5       |
        |1,4    | 6       |
        |1,3    | 7       |
        |1,5    | 8       |
        |2,3    | 8       |
        |2,4    | 10      |
        |2,5    | 11      |s
        |3,4,5  | 12      |
        |5,4    | 13      |
      
    Scenario Outline: test hold four dice reroll one
      Given player recieves dice and <hold> four
      When player rolls the four other dice that are not among the <hold>
      And player choose <category> to score in
      Then player get points for that <category>
      
      Examples: 
        | hold      | category| 
        |2,3,4,5    | 5       |
        |1,3,4,5    | 5       |

      
      
      
      
      

 