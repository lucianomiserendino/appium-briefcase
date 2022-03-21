@Smoke 
Feature: Sync documents for a specific Referral


Scenario: Verify the individual case referrals have a Sync link in the Case Information panel that downloads
 the documents in that particular case.

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	And Verify the individual case referrals have a Sync link in the Case Information panel that downloads the documents in that particular case.

				
		
