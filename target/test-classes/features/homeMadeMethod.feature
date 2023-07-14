Feature: Validating Signin
@Homemade
Scenario Outline: Verify if user is able to signin if they provide valid credentials
	Given User has launched the app and signin page displayed
	When user provide correct "<email>" and "<password>" 
	Then click on signin button
	And plans page opens
	
Examples:
	|email 	 | password|
	|shalini3003@yopmail.com |Test@2006|
#	|||
	
	
