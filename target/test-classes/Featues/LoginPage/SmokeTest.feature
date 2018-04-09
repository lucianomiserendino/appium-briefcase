Feature: Login Action 
#@Smoke 
Scenario: Successful Login with Valid Credentials 

	Given  User Navigates to Sever 
	When And User enters Crdenetials to Login
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev
	
	
	#Scenario: Successful LogOut 
	#When User LogOut from the Application 
	#Then Message displayed LogOut Successfully