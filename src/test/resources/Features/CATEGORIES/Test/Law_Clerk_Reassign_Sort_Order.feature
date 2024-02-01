Feature: Duplicating & Sorting law clerk assignment


@AMB-3774
Scenario:
Assignments are sorted by case number descending

	Then User gets the si_value from the site table
	|si_value      |courtId    |
	|displayTools  |test       |
  
		Given I am logged into Briefcase 
		|environment|sysadminUserName| sysadminPassword |courtId|judgeUserName|judgePassword|user    |
		|test       |test            | test             |test   |test         |test         |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			

	And user taps the Tools icon, then select an Existing Law Clerk and verifies the Assignments are sorted by case number descending
				
		
		
		
		
		
		
@AMB-4019
Scenario:
Duplicating law clerk assignment

	Then User gets the si_value from the site table
	|si_value      |courtId    |
	|displayTools  |test       |
  
		Given I am logged into Briefcase 
		|environment|sysadminUserName| sysadminPassword |courtId|judgeUserName|judgePassword|user    |
		|test       |test            | test             |test   |test         |test         |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
		Then Verify that user is prevented from creating duplicate lwk assignments
		
	#Then User selects random judge category
	#	|courtId|
	#	|test   |
			
	#Then User selects random case number
	#	|courtId|
	#	|test   |
				
	#Then User selects action
	#|dpf       |courtId    |
	#|chmAssign |test       |
		
 # Then user  verifies that briefcase events include the chmAssign DPF
	#	|caseNumber|courtId|
	#	|test      |test   |	

	#And User ensures there is a law clerk assignment of a specific type in a specific referral
	
	#Then User ensures there is another law clerk assignment in the same referral of the same type, but assigned to a different law clerk
	
	#Then Navigates to Tools category and selects one of the existing law clerks
	
	
	
	
