@Regression  @AMB-1038
Feature: Display Actions Panel and actions 

@Smoke
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
		
	Then User selects "MOTION" and "15-3314" 
	And User verifies the correct "Actions" display for the selected referral 
	
		|courtId|cmr_id |
		|test   |2310499|
		
	

Scenario: If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       
	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Integration    |sysadmin haenni| Test2021!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Williams     |
		
	
	Then User selects "MOTION" and "12-3348" 
	And User verifies the correct "Actions" display for the selected referral 
	
		|courtId|cmr_id |
		|cmja  |2667   |
			

		
		
		
		
		
		
		
		
		