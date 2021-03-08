 @Smoke @AMB-1021
Feature: Staff Assignments 



Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
	 This task is to verify that staff assignments are displaying on the referral list page

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |JAHaenni| Test2024!|test   |

	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
		
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User observes a collapsible panel entitled "Assignments" displays 
	And User verifies the staff assignments associated with the referral by using "<dbType>" , "<cmr_cs_caseid>" , "<cha_ju_pe_id>" , "<cmr_cyv_code>" , 
	Examples: 
		|dbType |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum|refCat  |
		|CMKA   |32          |82226        |CMKA  |motpet      |15-3314|MOTION  |
		
		
		

				
		