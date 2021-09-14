 Feature: Display Actions Panel and actions 

@S @Smoke @AMB-1038
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
		
	Then User selects "MOTION" and "15-3314" 
	And User verifies the correct "Actions" display for the selected referral 
	
		|courtId|cmr_id |
		|test   |2310499|
		


Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       
	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Testing        |sysadmin haenni| Test2022!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Williams     |
		
	Then User selects "PETITION" and "20-42000" 
	And User verifies the correct "Actions" display for the selected referral 
	
		|courtId|cmr_id |
		|test   |1331   |
		

	
		
		
		
		
		
		
		
		