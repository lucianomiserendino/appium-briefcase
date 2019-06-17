@AMB @Smoke @AMB-973
Feature: Number of cases displayed for non-orally argued cases 

 
Scenario Outline: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
#	Then User  Observes the categories on db "<dbType>"  and on the dashboard page ( "<cmr_cyv_code>" ) with judgeName "<judgeName>" and "<PE_RT_CODE>"
	
	
	
	Examples: 
		|environment   |userName          |password  |server        |judgeName |dbType |cmr_cyv_code|PE_RT_CODE|
		|INTEGRATION   |chambers courtney |Test2023! |CMKA          |Colloton  |CMKA   | lbrrpt     |jud       |
		#|Testing       |judgewilliams   |Testpass1!  |CM3A |Williams  |CM3A|  | 
		
		
	