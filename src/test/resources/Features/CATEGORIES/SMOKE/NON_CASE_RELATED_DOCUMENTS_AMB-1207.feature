@smoke @AMB-1207 @Regression
Feature: Non case related docs - Suppress case number for 'lbrrpt' category 


Scenario: User shouldn't see  caseNumber when view documents that are not case related
	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	Then I verify that the referral detail page only displays documents if chm_mobile_referral.cmr_cyv_code = lbrrpt 
		|courtId |jud   |
		|test    |test  |
		
		
	
