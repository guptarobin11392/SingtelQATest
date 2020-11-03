Feature: Reminder Management 

Background: User is on the ToDoMVC Home Page 
	Given User launchs "Chrome" Browser 
	When User navigate to "http://todomvc.com/examples/vue/" 
	Then User should see the ToDo MVC Home Page 
	
	#Completion logic Under UL(todo-list) tag, all the li tag should have class as "todo completed"
	
Scenario: As a user I should see the focus to be in Reminder box when I launch the website 
	Given I am on the TODOMVC Home Page 
	Then I should see cursor focus on "REMINDER_INPUT"
	
Scenario Outline: As a user I should be able to add task to my to-do list 
	Given I am on the TODOMVC Home Page 
	When I add <ReminderName> as a new reminder 
	Then I should see <ReminderName> in the reminder list 
	
	Examples: 
		| ReminderName  |
		| "Reminder 1" |
		| "Reminder 2" |
		
		
		
Scenario: As a user I should be able to see the count of my tasks 
	Given I am on the TODOMVC Home Page 
	When I add 10 reminders 
		| "Reminder 1" |
		| "Reminder 2" |
		| "Reminder 3" |
		| "Reminder 4" |
		| "Reminder 5" |
		| "Reminder 6" |
		| "Reminder 7" |
		| "Reminder 8" |
		| "Reminder 9" |
		| "Reminder 10" |
	Then I should see Reminder count as 10 
	

Scenario: As a user I should be able to add multiple tasks to my to-do list 
	Given I am on the TODOMVC Home Page 
	When I add 100 random tasks to my to-do list 
	Then I should see Reminder count as 100 
	
Scenario: As a user I should be able to add different type of values as reminder 
	Given I am on the TODOMVC Home Page 
	When I add 10 reminders 
		| "Reminder 1" |
		| "_________________Reminder 2" |
		| "!!!!!!Reminder 3" |
		| "!@#$%^^^&&&**___" |
		| "1231456798798" |
		| "<><><><>" |
		| "   Reminder1    " |
		| "Reminder 8" |
		| "Reminder 9" |
		| "Reminder 10" | 
	Then I should see Reminder count as 10 

	
Scenario: As a user I should not be able to add blank value as reminder 
	Given I am on the TODOMVC Home Page 
	When I add "      " as a new reminder 
	Then I should see Reminder count as 0 
	
Scenario: 
	As a user I should see that my task is added and is trimmed with white spaces 
	Given I am on the TODOMVC Home Page 
	When I add "   Reminder1   " as a new reminder 
	Then I should see "Reminder1" in the reminder list 
	

Scenario: As a user I should be able to delete my task from the to-do list 
	Given I am on the TODOMVC Home Page 
	When I add "Reminder 1" as a new reminder 
	And I add "Reminder 2" as a new reminder 
	Then I should see Reminder count as 2 
	When I delete "Reminder 1" task 
	Then I should see Reminder count as 1 
	And I should not see "Reminder1" in the reminder list 


Scenario: As a user I should be able to complete a reminder task 
	Given I am on the TODOMVC Home Page 
	When I add "Reminder 1" as a new reminder 
	And I complete "Reminder 1" task 
	Then I should see "Reminder 1" task status as complete 
	Then I should see Reminder count as 0 


Scenario: As a user I should be able to complete some reminder task out of all 
	Given I am on the TODOMVC Home Page 
	When I add 10 reminders 
		| "Reminder 1" |
		| "Reminder 2" |
		| "Reminder 3" |
		| "Reminder 4" |
		| "Reminder 5" |
		| "Reminder 6" |
		| "Reminder 7" |
		| "Reminder 8" |
		| "Reminder 9" |
		| "Reminder 10" | 
	And I complete "Reminder 5" task 
	Then I should see "Reminder 5" task status as complete 
	And I should see following task status as active 
		| "Reminder 1" |
		| "Reminder 2" |
		| "Reminder 3" |
		| "Reminder 4" |
		| "Reminder 6" |
		| "Reminder 7" |
		| "Reminder 8" |
		| "Reminder 9" |
		| "Reminder 10" | 
	And I should see Reminder count as 9 

Scenario: As a user I should be able to clear completed reminder task 
	Given I am on the TODOMVC Home Page 
	When I add "Reminder 1" as a new reminder 
	And I add "Reminder 2" as a new reminder 
	Then I should see Reminder count as 2 
	And I complete "Reminder 1" task 
	Then I should see Clear Completed button 
	When I click on clear completed button 
	Then I should not see "Reminder1" in the reminder list 
	And I should see Reminder count as 1 
	
Scenario: As a user I should be able to retain a completed reminder task 
	Given I am on the TODOMVC Home Page 
	When I add "Reminder 1" as a new reminder 
	And I complete "Reminder 1" task 
	Then I should see "Reminder 1" task status as complete 
	And I should see Reminder count as 0 
	When I uncheck the "Reminder 1" task to retain it 
	Then I should see "Reminder 1" task as Active 
	And I should see Reminder count as 1 
	
Scenario: As a user I should be able to change my reminder text 
	Given I am on the TODOMVC Home Page 
	When I add "Reminder 1" as a new reminder 
	Then I should see Reminder count as 1 
	When I change the task name from "Reminder 1" to "Reminder 2" 
	Then I should not see "Reminder1" in the reminder list 
	And I should see "Reminder2" in the reminder list 
