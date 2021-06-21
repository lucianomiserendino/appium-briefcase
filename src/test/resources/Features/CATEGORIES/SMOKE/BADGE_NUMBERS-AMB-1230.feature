@Smoke @AMB @AMB-1230
Feature: Badge numbers in the navigation 


Scenario: 
	Verify Badge numbers in the navigation 
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
	
			
			
			
		