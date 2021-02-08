@AMB @Smoke @AMB-1207 @a
Feature: Non case related docs - Suppress case number for 'lbrrpt' category 

Background: 

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	
	
Scenario: 
	User shouldn't see  case number when view documents that are not case related
  
	Given  In "CMKA" , If the chm_mobile_referral.cmr_cyv_code = "lbrrpt" , verify  cyv_category  displays on the Dashboard page. Verify case number don't display for  referrals where the chm_mobile_referral.cmr_cyv_code = 'lbrrpt', Verify only  documents display the referral detail page. Verify any actions, assignment, or additional case information don't  display.Use PE_RT_CODE "jud" and judge "Colloton"