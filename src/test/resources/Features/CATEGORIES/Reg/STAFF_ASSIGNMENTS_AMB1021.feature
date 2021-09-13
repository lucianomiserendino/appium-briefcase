Feature: Staff Assignments 


 @Smoke @AMB-1021
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
	 This task is to verify that staff assignments are displaying on the referral list page

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
		
	Then User selects "MOTION" and "15-3314" 
	Then User observes a collapsible panel entitled "Assignments" displays 
	And User verifies the staff assignments associated with the referral by using "<dbType>" , "<cmr_cs_caseid>" , "<cha_ju_pe_id>" , "<cmr_cyv_code>" , 
	
	Examples: 
		|dbType |cha_ju_pe_id|cmr_cs_caseid|cmr_cyv_code|refCat  |
		|CMKA   |32          |82226        |motpet      |MOTION  |
		
		
		
		
	@cm3a
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
	 This task is to verify that staff assignments are displaying on the referral list page

       
	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Testing        |sysadmin haenni| Test2022!|test   |
		
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Williams     |
		
	Then User selects "PETITION" and "20-42000" 
	Then User observes a collapsible panel entitled "Assignments" displays 
	And User verifies the staff assignments associated with the referral by using "<dbType>" , "<cmr_cs_caseid>" , "<cha_ju_pe_id>" , "<cmr_cyv_code>" , 
	
	Examples: 
		|dbType |cha_ju_pe_id|cmr_cs_caseid|cmr_cyv_code|refCat  |
		|CM3A   |2189563     |69318        |rhr         |PETITION|
		

		
		

				
		