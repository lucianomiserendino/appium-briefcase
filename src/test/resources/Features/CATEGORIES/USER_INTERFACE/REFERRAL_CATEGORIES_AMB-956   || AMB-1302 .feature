@Smoke @AMB-956 @n

Feature: Referral Categories display on the dashboard for the judge 

Scenario: 
	Referral categories display on the dashboard for the judge 

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|jud       |courtId       |
		|test      |test          |
		
		
		#NOTE:  The pending tasks category will not be included in this task.  
		#This category is dynamically created if there are judge assignments. 
		# The bookmarked category is also not included in this task.
		
		
