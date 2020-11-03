Feature: UserInterace TODOMVC Web Page 

Scenario: User is able to access ToDoMVC Home Page on Chrome browser 
	Given     User launchs "Chrome" Browser 
	When     User navigate to "http://todomvc.com/examples/vue/" 
	Then     User should see the ToDo MVC Home Page 
	
Scenario: User is able to access ToDoMVC Home Page on Firefox browser 
	Given     User launchs "Firefox" Browser 
	When     User navigate to "http://todomvc.com/examples/vue/" 
	Then     User should see the ToDo MVC Home Page 
	
Scenario: User is able to access ToDoMVC Home Page on IE browser 
	Given     User launchs "IE" Browser 
	When     User navigate to "http://todomvc.com/examples/vue/" 
	Then     User should see the ToDo MVC Home Page