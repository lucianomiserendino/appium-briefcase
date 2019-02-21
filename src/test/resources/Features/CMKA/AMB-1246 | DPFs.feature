Feature: Execute multiple DPFs in the same action 



@AMB-1246 
Scenario Outline: 
	This task is to verify the Data is saving when executing multiple DPFs in the same action
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "judge werner" and "Test2019!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User  selects action using dbType "<dbType>" and  "<actionElID>"  and verifies the name of the action displays in the dark blue banner 
	Then create a staff assignment  by using "<dbType>", "chmAssign" , "<actionElID>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>" 
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	Then user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using "<dbType>", "note" , "<actionElID>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
	Then user adds a Note in the comment field 
	
	
	
	Examples: 
		|dbType |actionElID   |cha_ju_pe_id|cmr_cs_caseid|dbtype|cmr_cyv_code|refCat                 |caseNum |
		|CMKA   |3155         |34          |82885        |CMKA  |prhr        |PETITIONS_FOR_REHEARING|18-12418|
	