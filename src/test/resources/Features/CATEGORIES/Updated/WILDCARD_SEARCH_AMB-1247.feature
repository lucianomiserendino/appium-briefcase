@Regression
Feature: Case Query -- return case list for wildcard searches 
 
 @AMB-1247 @smoke
Scenario: 

	This task is to verify  the app returns a result after performing a valid wildcard case search

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
   Then User selects a random category

	Then User taps on magnifying glass icon and searches for a case and  verifies the result is not empty
	|caseNumber|
	|test      |
	
	
	
	@AMB-5116
	Scenario: 

	This task is to verify  the user is unable to access Search function while offline

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		

	Then User turns off the wifi and verifies that Search function is disabled 
	
	
	