@AMB @Regression @AMB-1247
Feature: Case Query -- return case list for wildcard searches 
 
Scenario: 

	This task is to verify  the app returns a result after performing a valid wildcard case search
		Given user is logged into Briefcase 
		|environment|userName|password |server|
		|INTEGRATION|s haenni|Test2022!|CMKA  |
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	When User selects a  "Test Automation" 
	Then User taps on magnifying glass icon and searches for case and  verifies the app returns a result