Feature: Duplicating & Sorting law clerk assignment


@AMB-3774 @Regression
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
				
		
		
		
		
		
		
@AMB-4019 @Regression
Scenario:
Duplicating law clerk assignment

	Then User gets the si_value from the site table
	|si_value      |courtId    |
	|displayTools  |test       |
  
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
		Then Verify that user is prevented from creating duplicate lwk assignments
		
	
	
