Feature: Number of cases displayed for non-orally argued cases

Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	#Login as Chambers Courtney // Test2020!
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 

@Smoke
	@AMB_973
Scenario: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.
    
	Given User finds the valid non-orally argued categories for the judge 