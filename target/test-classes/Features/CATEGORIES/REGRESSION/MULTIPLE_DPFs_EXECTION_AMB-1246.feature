Feature: Execute multiple DPFs in the same action 



@AMB-1246 
Scenario Outline: 
	This task is to verify the Data is saving when executing multiple DPFs in the same action
	#Given I am logged into Briefcase 
	#	|environment    |userName     | password |courtId|
	#	|Integration    |Judge Werner | Test2025!|test   |

	
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
		
	Then create a staff assignment. "chmAssign" , "<actionElID>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>" 
	#Then User verifies the text "Add New Note" displays in the light blue banner. 
	#Then user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using "<dbType>", "note" , "<actionElID>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
	#Then user adds a Note in the comment field 
	
	
	Examples: 
		|dbType |el_id    |cha_ju_pe_id|cmr_cs_caseid|dbtype|cmr_cyv_code|refCat   |caseNum |dpfName  |
		|CMKA   |3164     |34          |82885        |CMKA  |prhr        |PETITION |18-12418|multiple |
		
		
