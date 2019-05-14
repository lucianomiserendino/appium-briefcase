Feature: Display assignments when briefcaseTargetOnly = n



@AMB-1350
Scenario: 
	Given User sets the "briefcaseTargetOnly" site var to "n" on "CMKA" 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "MOTIONS_PETITIONS" and "15-3314" 
   	When User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel
