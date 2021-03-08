@Smoke @AMB-1123 @AMB-1137 @AMB-1170 @AMB-1173 @S
Feature: chmAssign 


Scenario Outline: 
	This task is to verify that a chambers user is able to create a new staff assignment,
	to verify back-end updates when a new staff assignment is created,
	edit existing staff assignments and verify Back-end after modifying assignment 
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	Then  User selects "<refCat>" and "<caseNum>" 
	
	Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
	Then user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using "<dbType>", "<dpfName>" , "<el_id>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
	#follow steps in @AMB-1123,@AMB-1137,@AMB-1170,@AMB-1173)
	
	
	Examples: 
		|dbType |el_id     |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum|refCat   |dpfName  |
		|CMKA   |3116      |32          |82226        |CMKA  |prhr        |15-3314|PETITION |chmAssign|
		
		