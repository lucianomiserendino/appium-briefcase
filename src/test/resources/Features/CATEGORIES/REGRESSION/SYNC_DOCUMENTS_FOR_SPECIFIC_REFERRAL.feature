@AMB-2700 
Feature:  Sync documents for a specific Referral

Scenario: 
Verify the individual case referrals have a Sync link in the Case Information panel that downloads 
the documents in that particular case.


	#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
       Then I select a user
      | userType | personrole      | stf             |
      | stf      | Staff Attorneys | Brown, Benjamin |
		
		Then User selects random stf Aty category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		
	Then Verify that tapping the link in the Case Info panel downloads all original documents

		
		
		
