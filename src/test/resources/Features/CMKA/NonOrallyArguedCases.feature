Feature: Number of cases displayed for non-orally argued cases 

Background: 

	Given  User Navigates to environment 
	When  User enters Credentials to Login 
		|userName			|password	|
		|chambers courtney|Test2020! |
		
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	
	
	
@Smoke_CMKA
@AMB_973_CMKA
Scenario: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.
    
	Given  user selects a server "Appellate DC Development - CMKA" 
	Then User  Observes the categories on the dashboard page (CMKA) 
	
	
@Smoke_CM5A
@AMB_973_CM5A 
Scenario: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.
    
	Given  user selects a server "Appellate DC Development - CM5A" 
	Then User  Observes the categories on the dashboard page (CM5A) 
	
	
	
	
	
	
	