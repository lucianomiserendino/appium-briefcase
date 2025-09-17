@Regression
Feature: Sorting law clerk assignment


@AMB-3774
Scenario:
Assignments are sorted by case number descending

	Then User gets the si_value from the site table
	|si_value      |courtId    |
	|displayTools  |test       |
  
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			

	And user taps the Tools icon, then select an Existing Law Clerk and verifies the Assignments are sorted by case number descending
				
		

	
