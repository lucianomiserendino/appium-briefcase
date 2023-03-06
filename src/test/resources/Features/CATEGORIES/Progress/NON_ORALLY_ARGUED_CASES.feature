@Regression @AMB-3443
Feature:  Non-orally Argued Cases

		
Scenario:
Verify if there is more than one referral in the same category for a case, the case is displayed only once.
Verify the referral date that is displayed is for the latest referral.

	
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		
	#Given User observes the "Vote_Information" panel displays.   This should only display if the referral requires voting 
	
	Then User verifies if there is more than one referral in the same category for a case, the case is displayed only once and the referral date that is displayed is for the latest referral.