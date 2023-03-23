Feature: Vote Information - En banc panel

@AMB-3474
Scenario: 
	Verify if the matter was referred to an en banc panel, instead of listing all the judges on the page, a button is displayed next 
	to the logged in judge’s vote that when tapped, shows all the judges’ vote in a popup. (v1.2.x) (line 243)
	
		#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
        Then user selects a category and case with en banc panel
	
	  	Given User observes the "Vote_Information" panel displays.   This should only display if the referral requires voting 

        Then User verifies that a button is displayed next to the logged in judge’s vote 
		
        When it's tapped, it shows all the judges’ vote in a popup.
