@smoke @AMB-1230 @Regression @updated
Feature: Badge numbers in the navigation 


Scenario: 
	Verify Badge numbers in the navigation 
	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
		
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
