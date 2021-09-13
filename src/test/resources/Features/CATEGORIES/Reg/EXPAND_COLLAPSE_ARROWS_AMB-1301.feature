@Smoke @AMB-1301 
Feature: Verify data is displayed on the Dashboard after tapping the left-hand navigation expand/collapse arrows 



Scenario: 

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	Then user taps on left-hand navigation "Expand" arrows 
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|briefcaseUser|courtId       |
		|Colloton     |test          |
	Then user taps on left-hand navigation "Expand" arrows 
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|briefcaseUser|courtId       |
		|Colloton     |test          |	
		
		
	@cm3a 
Scenario: 

	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Testing        |sysadmin haenni| Test2022!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Williams     |
		
	Then user taps on left-hand navigation "Expand" arrows 
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|briefcaseUser|courtId       |
		|Williams     |test          |
	Then user taps on left-hand navigation "Expand" arrows 
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|briefcaseUser|courtId       |
		|Williams     |test          |	
		
		
		
		