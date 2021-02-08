@AMB @Smoke @AMB-973  @a
Feature: Number of cases displayed for non-orally argued cases 


Scenario Outline: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.


	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
		
	Then User  Observes the categories on db and on the dashboard page ( "<cmr_cyv_code>" ) with judgeName "<judgeName>" and "<PE_RT_CODE>" 
		|courtId |
		|CMKA    |
		
	Examples: 
		|judgeName |cmr_cyv_code|PE_RT_CODE|
		|Colloton  |lbrrpt      |jud       |
		
		
	