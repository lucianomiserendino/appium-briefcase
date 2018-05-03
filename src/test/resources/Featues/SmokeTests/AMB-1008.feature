Feature: Pending Tasks Category Displays on the Dashboard 

Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	#Login as Chambers Courtney // Test2020!
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
@Smoke	
@AMB_1008 
Scenario: 
	A category entitled "Pending Tasks" will display on the dashboard if the judge has 
	any pending assignments and the site table variable briefcaseShowPendingTasks ='y'. 
	
	Given If The judge has any pending assignments it will validate the total num of pending task on UI with DB 
