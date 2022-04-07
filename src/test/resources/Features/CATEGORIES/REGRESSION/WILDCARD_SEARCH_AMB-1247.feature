@t @AMB @Regression @AMB-1247
Feature: Case Query -- return case list for wildcard searches 
 
Scenario: 

	This task is to verify  the app returns a result after performing a valid wildcard case search

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
	
	Then I select a user 
		|userType   |personrole        |jud          |
		|judge      |Appellate Judges  |test         |

	Then User taps on magnifying glass icon and searches for a case and  verifies the result if not empty
	|caseNumber|
	|test      |