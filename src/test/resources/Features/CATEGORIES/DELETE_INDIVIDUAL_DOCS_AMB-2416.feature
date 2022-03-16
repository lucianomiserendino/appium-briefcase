Feature: Ability to Delete Individual Docs for Statff Attorneys

  @AMB-2416
  Scenario: As a Staff Attorney I can delete individual documents

	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.

	#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |stf  |
		|stf        |Staff Attorneys   |test |
		
#	Given I tap on referral category, the categories can be found using 'RA_PE_ID' : "434" 
	Then User selects random stf Aty category
		|courtId|
		|test   |

