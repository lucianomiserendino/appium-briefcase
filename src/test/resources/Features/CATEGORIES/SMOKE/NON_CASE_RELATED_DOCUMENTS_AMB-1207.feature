@Smoke @AMB-1207
Feature: Non case related docs - Suppress case number for 'lbrrpt' category 


Scenario: User shouldn't see  caseNumber when view documents that are not case related
	
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud        |
		|judge      |Appellate Judges  |test       |
	Then I verify that the referral detail page only displays documents if chm_mobile_referral.cmr_cyv_code = lbrrpt 
		|courtId |jud   |
		|test    |test  |
		
		
	
