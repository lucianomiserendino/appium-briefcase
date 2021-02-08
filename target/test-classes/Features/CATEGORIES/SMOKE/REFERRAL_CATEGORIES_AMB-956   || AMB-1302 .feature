@sysadmin @AMB @Smoke @AMB-956 @smoke  @a
Feature: Referral Categories display on the dashboard for the judge 


Scenario: 
	Referral categories display on the dashboard for the judge 

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
	Then I select a user 
	|role              |briefcaseUser|
    |Appellate Judges  |Colloton     |
		
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|briefcaseUser|courtId       |
		|Colloton     |test          |
		
		
		#NOTE:  The pending tasks category will not be included in this task.  
		#This category is dynamically created if there are judge assignments. 
		# The bookmarked category is also not included in this task.