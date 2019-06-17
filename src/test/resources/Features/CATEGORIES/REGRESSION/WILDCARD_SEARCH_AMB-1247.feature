@AMB @Regression @AMB-1247
Feature: Case Query -- return case list for wildcard searches 
 
Scenario: 

	This task is to verify  the app returns a result after performing a valid wildcard case search
	Given  User Navigates to  "INTEGRATION" environment 
	When   User enters Credentials to Login "s haenni" and "Test2021!" 
	And  User clicks on Send Key to Device 
	Then  User navigates to MobileBrifcase App 
	And   user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	When User selects a  "Test Automation" 
	Then User taps on magnifying glass icon and searches for case and  verifies the app returns a result