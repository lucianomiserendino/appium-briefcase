Feature: Badge numbers in the navigation 

@Smoke @AMB @AMB-1230
Scenario: 
	Verify Badge numbers in the navigation 
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
	
	
	
@cm3a
Scenario: 
	Verify Badge numbers in the navigation 
	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Testing        |sysadmin haenni| Test2022!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Williams     |
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
	
	
		