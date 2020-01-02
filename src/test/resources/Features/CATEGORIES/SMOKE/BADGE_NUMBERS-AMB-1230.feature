@AMB @Smoke @AMB-1230 
Feature: Badge numbers in the navigation 


Scenario: 
	Verify Badge numbers in the navigation 
	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
	
			