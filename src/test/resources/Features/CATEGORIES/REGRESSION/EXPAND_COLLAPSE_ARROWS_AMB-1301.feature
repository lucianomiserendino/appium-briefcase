@AMB @Regression @AMB-1301
Feature: Verify data is displayed on the Dashboard after tapping the left-hand navigation expand/collapse arrows 

Scenario: 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "chambers courtney" and "Test2023!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then user taps on left-hand navigation "Expand" arrows 
	And User Observes the referral categories that display on "CMKA" and on the dashboard using  "Colloton" and "jud". 
	Then user taps on left-hand navigation "Expand" arrows 
	And User Observes the referral categories that display on "CMKA" and on the dashboard using  "Colloton" and "jud". 
	