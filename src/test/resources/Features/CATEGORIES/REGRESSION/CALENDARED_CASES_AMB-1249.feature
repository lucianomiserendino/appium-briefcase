@AMB @Regression @AMB-1249 
Feature: Display correct days for calendared cases 

Scenario: 
	Verify  that days  for calendared cases are displayed correctly
	
	Given user is logged into Briefcase 
		|environment|userName|password |server|
		|INTEGRATION|s haenni|Test2022!|CMKA  |
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	When User selects a  "Cases on Calendar" 
	Then User selects a session and verifies days are displayed corrcetly in that session,DB is  "CMKA" and judge's peID is "32"