@AMB-2327 @Regression
Feature: chmSilentAssign DPF 

Scenario Outline: chmSilentAssign DPF - Multiple assignments in a single transaction
	
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |Benton   |
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
	Then User clicks on create New Staff Assignment	
	And User selects a staff member, assignment type, and at least one date, "<dbType>", "<dpfName>" , "<el_id>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
	Then User clicks on create New Staff Assignment	
	And User selects the same staff member, a different assignment type, and at least one date, "<dbType>", "<dpfName>" , "<el_id>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
		
	Examples: 
		|dbType |el_id     |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum |refCat         |dpfName  |
		|CMKA   |3116      |34          |82898        |CMKA  |autotst     |18-83118|TEST_AUTOMATION|chmAssign|
		
		