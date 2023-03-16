Feature: Sync with CMECF

@AMB-3444 
Scenario: 
	The Sync button is used to download any pending referrals and documents.  
	This task is just to verify that when tapping/clicking the Sync button for a judge or staff attorney, that the sync completes.
 

       
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
	Then User clicks/Tap Sync with CM/ECF button on the Dashboard page and verify the Sync completes. 
	

	
@AMB-2700
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
	