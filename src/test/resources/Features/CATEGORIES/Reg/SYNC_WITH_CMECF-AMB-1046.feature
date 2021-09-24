Feature: Sync with CMECF



@AMB-1046 
Scenario: 
	The Sync button is used to download any pending referrals and documents.  
	This task is just to verify that when tapping/clicking the Sync button for a judge or staff attorney, that the sync completes.
 

       
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
	Then User clicks/Tap Sync with CM/ECF button on the Dashboard page and verify the Sync completes. 
	
	

		