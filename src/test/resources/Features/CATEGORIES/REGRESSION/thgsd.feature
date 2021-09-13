Feature: dsnfd


@lllllll
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
	 This task is to verify that staff assignments are displaying on the referral list page

	#Given I am logged into Briefcase 
		#|environment    |userName| password |courtId|
		#|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
		
	When User selects a  "<refCat>" 

	Then hjgdf
	Examples: 
		|dbType |cha_ju_pe_id|cmr_cs_caseid|cmr_cyv_code|refCat    |
		|CMKA   |32          |82226        |motpet      |Reference |
		
		


