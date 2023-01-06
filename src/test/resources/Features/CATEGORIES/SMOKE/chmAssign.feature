Feature: chmAssign 

@AMB-1122 @AMB-1123 @AMB-1137 @AMB-1170 @AMB-1173 
Scenario: 
	This task is to verify that a chambers user is able to create a new staff assignment,
	to verify back-end updates when a new staff assignment is created,
	edit existing staff assignments and verify Back-end after modifying assignment 

       
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
				
	Then User  selects an action and verifies the name of the action displays in the dark blue banner 
	
		|courtId|
		|test   |
		
  Then user  verifies that briefcase events include the chmSilentAssign DPF
		|caseNumber|courtId|
		|test      |test   |
		
		And User creates a new staff assignment
		|courtId|
		|test   |
		
		Then User edits existing staff assignment
		|courtId|
	    |test   |
	    
	    And User terminates the assignment
   		|courtId|
	    |test   |
   