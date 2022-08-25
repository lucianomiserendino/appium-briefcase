@Smoke
Feature: note DPF UI 

@AMB-1055 @n
Scenario: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.

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
		
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	And user verifies an editable "Description" , "Comment" , "Submit" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF 
		|courtId|
		|test   |
			

    
    

    