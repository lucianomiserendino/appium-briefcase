Feature: Display Actions Panel and actions 
@p
@Smoke 
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       
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
   