@AMB-3443 @Regression
Feature:  Non-orally Argued Cases

		
Scenario:
Verify if there is more than one referral in the same category for a case, the case is displayed only once.
Verify the referral date that is displayed is for the latest referral.

	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
			
	Then User verifies if there is more than one referral in the same category for a case, the case is displayed only once and the referral date that is displayed is for the latest referral.