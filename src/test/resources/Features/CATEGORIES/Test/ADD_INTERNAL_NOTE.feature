@AMB-3104 @AMB-3286 @AMB-3384 @AMB-3386
Feature: Display an internal note option for judges



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
		
	And User creates a new internal note and verifies the note is saved/displayed in the referral detail and referral category page 
	
	Then User navigates away to a different category then back and verifies that the existing note still remains on the referral category and case detail page
	
	Then User closes and reopens the app
	
	And User verifies that the existing note still remains on the referral category and case detail page 
	
	Then User deletes the existing note and verifies that the note doesn't come back after navigating away to a different category then back
	
	Then User closes and reopens the app
	
	Then User verifies that the note doesn't come back after closing and reopening the app
	