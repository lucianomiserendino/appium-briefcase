Feature: Display correct days for calendared cases 

#@Regression 
@AMB-1249 
Scenario: 
	Verify  that days  for calendared cases are displayed correctly
	
	Given  User Navigates to  "INTEGRATION" environment 
	When   User enters Credentials to Login "s haenni" and "Test2021!" 
	And  User clicks on Send Key to Device 
	Then  User navigates to MobileBrifcase App 
	And   user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	When User selects a  "Cases on Calendar" 
	Then User selects a session and verifies days are displayed corrcetly in that session,DB is  "CMKA" and judge's peID is "32"