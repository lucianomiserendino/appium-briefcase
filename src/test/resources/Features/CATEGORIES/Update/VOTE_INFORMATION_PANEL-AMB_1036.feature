Feature: Display Vote Information Panel, filer info and judge's initials 
			
	
		@AMB-1097 
		Scenario Outline: 
			Given I am logged into Briefcase 
				|environment    |userName| password |courtId|
				|Integration    |s haenni| Test2025!|test   |
			Then I select a user 
				|userType   |personrole        |jud          |
				|judge      |Appellate Judges  |Colloton     |
			Then User selects "<refCategory>" and "<caseNum>" 
			
			Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
				|courtId|
				|test   |
			Then   In the judgeVoteDPf, the user observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use  "<cmr_ju_pe_id>" , "<cmr_cs_caseid>" ,"<cmr_cyv_code>" , "<ccr_id>" . 
				|courtId|
				|test   |
			Then  In the judgeVoteDPf, the user checks each judge's vote and the date displays under their initials, using  "<ccr_id>" 
				|courtId|
				|test   |
			Examples: 
				|el_id | refCategory     | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
				|3155  | PETITION        | 15-3314  |CMKA | 82226         |32          |prhr        |34870 |
				
				
			
						
								
								
								
								
								
								
								
								
								
								
								
								
								
		