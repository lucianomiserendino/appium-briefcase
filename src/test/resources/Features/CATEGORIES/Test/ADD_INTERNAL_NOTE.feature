Feature: Display an internal note option for judges
@AMB-3104, @AMB-3286


Scenario: Add an Internal Note for Judges
       
		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
	
	Then User expands/collapse panel
	
	And User verifies that there's an option for judges to create an internal note.
	