@Smoke @AMB @AMB-1230 @k
Feature: Badge numbers in the navigation 


Scenario: 
	Verify Badge numbers in the navigation 
	
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
