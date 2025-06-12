@Regression
Feature: Sync with CMECF

@AMB-3444 @AMB-1046
Scenario: 
	The Sync button is used to download any pending referrals and documents.  
	This task is just to verify that when tapping/clicking the Sync button for a judge, that the sync completes.
 

       
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	Then User taps Sync with CM/ECF button on the Dashboard page and verify the Sync completes. 
	

@AMB-3444 @AMB-1046
Scenario: 
	The Sync button is used to download any pending referrals and documents.  
	This task is just to verify that when tapping/clicking the Sync button for a staff attorney, that the sync completes.
 

       
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
		
			
		Then I select a user 
		|userType   |personrole       |stf      |user     |
		|stf        |Staff Attorneys  |test     |sysadmin | 
		
	Then User taps Sync with CM/ECF button on the Dashboard page and verify the Sync completes. 
	
	

@AMB-2700
Scenario: 
Verify tapping the Sync in the Referral list page downloads the documents for the selected judge category


		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		
		Then User records the number of available documents for download
		
        Then User selects a random category
		
	    Then User taps Sync button in the Referral List Page and verifies that all original documents are downloaded
	
	
	
	

	

	