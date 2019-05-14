Feature: Docket Entries for Chambers Users 

#@Regression
@AMB-1234 
Scenario: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then user gets the entries of the judge ( "15-2594" ) and logs out 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "chambers courtney" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then user verifies a JA or law clerk can see the same entries as their judge  ( "15-2594" ) 
	
	