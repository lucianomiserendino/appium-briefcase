Feature: Number of cases displayed for non-orally argued cases 

@AMB-973 @S
Scenario Outline: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.


	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Benton       |
		
	Then User  Observes the categories on db and on the dashboard page ( "<cmr_cyv_code>" ) with judgeName "<judgeName>" and "<PE_RT_CODE>" 
		|courtId |
		|CMKA    |
		
	Examples: 
		|judgeName |cmr_cyv_code|PE_RT_CODE|
		|Benton    |lbrrpt      |jud       |
		
		
	
	
@cm3a
	Scenario Outline: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.


	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Testing        |sysadmin haenni| Test2022!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Williams     |
		
	Then User  Observes the categories on db and on the dashboard page ( "<cmr_cyv_code>" ) with judgeName "<judgeName>" and "<PE_RT_CODE>" 
		|courtId |
		|CM3A    |
		
	Examples: 
		|judgeName |cmr_cyv_code|PE_RT_CODE|
		|Williams  |lbrrpt      |jud       |
		
		