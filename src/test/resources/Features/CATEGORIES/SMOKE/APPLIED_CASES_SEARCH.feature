Feature:  Prevent the user from navigating to an applied case referral detail page from Search


Background:
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		
@AMB-3136
Scenario:
As a judge user when entering an applied case number in the Search box and selecting 'On Device' ,
 the user is directed to the target case referral detail page.
The user is not directed to the applied case.
The applied case can be selected from the target case.

		
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
	
	Then User taps on magnifying glass icon and searches for applied case, selects On Device option & verifies that the user is directed to the target case referral detail page
	|userType |
	|judge    |
	

