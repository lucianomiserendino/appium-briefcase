@Regression @smoke
Feature: Execute multiple DPFs in the same action 



@AMB-1246 
Scenario: 
	This task is to verify the Data is saving when executing multiple DPFs in the same action
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
		
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
   Then User selects a random category
			
   Then User selects a random case
				
	Then User selects action
	|dpf       |courtId    |
	|multiple  |test       |
		
	And Submit action that has multiple dpfs
		
   Then User navigates to View Case Info, then taps Docket Entries
		
		And  verifies data is saving when executing multiple DPFs in the same action