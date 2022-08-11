@Smoke @AMB-1301 @n
Feature: Verify data is displayed on the Dashboard after tapping the left-hand navigation expand/collapse arrows 



Scenario: 

	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud       |
		|judge      |Appellate Judges  |test      |
	Then user taps on left-hand navigation "Expand" arrows 
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|jud     |courtId       |
		|test    |test          |
	Then user taps on left-hand navigation "Expand" arrows 
	And User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		|jud    |courtId       |
		|test   |test          |
