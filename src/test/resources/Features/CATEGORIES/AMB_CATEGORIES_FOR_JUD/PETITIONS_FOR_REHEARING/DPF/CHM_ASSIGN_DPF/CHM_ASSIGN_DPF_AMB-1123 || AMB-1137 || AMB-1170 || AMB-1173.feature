Feature: chmAssign 

@CMKA 
@Regression 
@AMB-1123 
@AMB-1137 
@AMB-1170 
@AMB-1173 
Scenario Outline: 
	This task is to verify that a chambers user is able to create a new staff assignment,
	to verify back-end updates when a new staff assignment is created,
	edit existing staff assignments and verify Back-end after modifying assignment 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User  selects action using dbType "<dbType>" and  "<actionElID>"  and verifies the name of the action displays in the dark blue banner 
	Then user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using "<dbType>", "<dpfName>" , "<actionElID>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
	#follow steps in @AMB-1123,@AMB-1137,@AMB-1170,@AMB-1173)
	
	
	Examples: 
		|dbType |actionElID   |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum|refCat                 |caseNum|dpfName  |
		|CMKA   |3155         |32          |82226        |CMKA  |prhr        |15-3314|PETITIONS_FOR_REHEARING|15-3314|chmAssign|