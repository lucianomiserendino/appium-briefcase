@AMB @Regression @AMB-1247 @a
Feature: Case Query -- return case list for wildcard searches 
 
Scenario: 

	This task is to verify  the app returns a result after performing a valid wildcard case search

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
	When User selects a  "Motion" 
	Then User taps on magnifying glass icon and searches for "12-3348" and  verifies the app returns a result