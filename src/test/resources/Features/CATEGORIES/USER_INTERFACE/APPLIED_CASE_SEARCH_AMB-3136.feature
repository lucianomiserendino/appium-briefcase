@AMB-3136
Feature: Prevent the user from navigating to an applied case referral detail page from Search 
 
Scenario: 
As a user when entering an applied case number in the Search box and selecting 'On Device' , the user is directed to the target case referral detail page.
The user is not directed to the applied case.
The applied case can be selected from the target case.

		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
		
		
	Then User selects random target case
	
	Then User selects random applied case
	
	Then User taps on magnifying glass icon and searches for applied case, selects On Device option & verifies that the user is directed to the target case referral detail page