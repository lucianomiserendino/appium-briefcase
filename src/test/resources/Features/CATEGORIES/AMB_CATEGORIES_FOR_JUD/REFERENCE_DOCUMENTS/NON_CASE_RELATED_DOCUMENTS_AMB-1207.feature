Feature: Non case related docs - Suppress case number for 'lbrrpt' category 

Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	

@Smoke	
@AMB-1207 
Scenario: 
	User shouldn't see  case number when view documents that are not case related
  
	Given  In "CMKA" , If the chm_mobile_referral.cmr_cyv_code = "lbrrpt" , verify  cyv_category  displays on the Dashboard page. Verify case number don't display for  referrals where the chm_mobile_referral.cmr_cyv_code = 'lbrrpt', Verify only  documents display the referral detail page. Verify any actions, assignment, or additional case information don't  display.Use PE_RT_CODE "jud" and judge "Colloton"