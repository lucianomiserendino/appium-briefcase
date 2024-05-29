@AMB-973
Feature: Number of cases displayed for non-orally argued cases 

Scenario: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.


	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	Then I find the valid non-orally argued categories for the judge 
	
		|courtId |jud   |
		|test    |test  |
		
		
		
		
		
		
		