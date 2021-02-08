@AMB @Regression @AMB-1234 
Feature: Docket Entries for Chambers Users 

Scenario: 

	Given user is logged into Briefcase 
		|environment|userName|password |server|
		|INTEGRATION|s haenni|Test2022!|CMKA  |
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then user gets the entries of the judge ( "15-2594" ) and logs out 
	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
	Then user verifies a JA or law clerk can see the same entries as their judge  ( "15-2594" ) 
	
	