Feature: Execute multiple DPFs in the same action 



@AMB-1246 
Scenario Outline: 
	This task is to verify the Data is saving when executing multiple DPFs in the same action
		#Given I am logged into Briefcase 
		#|environment    |userName| password |courtId|
		#|Integration    |s haenni| Test2024!|test   |
		
		Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	Then User selects "<refCategory>" and "<caseNum>" 
	
	Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
		
		
	And User creates a new staff assignment
		
	Examples: 
		|el_id | refCategory     | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
		|3164  | PETITION        | 15-3314  |CMKA | 82226         |32          |prhr        |34870 |
		
		
		