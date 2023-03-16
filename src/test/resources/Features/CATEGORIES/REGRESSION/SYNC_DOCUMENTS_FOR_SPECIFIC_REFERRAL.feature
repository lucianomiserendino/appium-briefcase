 Feature:  Sync documents for a specific Referral

		
		
@AMB-3198		
Scenario: 
Verify Case document sync decreases the total count in the device Sync button correctly


		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
      
     And note the total documents available for download count in the Sync button on the Dashboard
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
	
	Then User expands/collapse panel
		
	Then Verify that tapping the link in the Case Info panel downloads all original documents, decreases the total count in the device Sync button correctly
		
		
		