Feature: Display Vote Information Panel, filer info and judge's initials 
	Display Actions Panel and actions

	#dependent on AMB-2257
@Regression @AMB-1036 
Scenario Outline: 
	If a referral requires voting, a collapsible Vote Information panel displays.  When expanded, it displays the following information:
1.  The name and party type of the person who filed the motion/petition
2.  The judges initials who are on the panel
3.  The relief on which the judge is ruling
4.  Each judge's vote and the date they voted

	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
		
  Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	Given User observes ( "<dbType>" ) the "Vote_Information" panel displays.   This should only display if the referral requires voting 
	Then   for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use  "<cmr_ju_pe_id>" , "<cmr_cs_caseid>" ,"<cmr_cyv_code>" , "<ccr_id>" . 
		|courtId|
		|test   |
	Then  User checks each judge's vote  and the date  displays under their initials, using  "<ccr_id>" 
		|courtId|
		|test   |
		
	Examples: 
		|server         |refCategory  | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
		|CMKA           |PETITION     | 15-3314  |CMKA  | 82226         |32          |prhr       |34870 |
		
		
		
		@Smoke @AMB-1097 
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
				
				
				
				@AMB-2534 
				Scenario Outline: 
					Given I am logged into Briefcase 
						|environment    |userName| password |courtId|
						|Integration    |s haenni| Test2025!|test   |
					Then I select a user 
						|userType   |personrole        |jud          |
						|judge      |Appellate Judges  |Colloton     |
						
					Then User selects "<refCategory>" and "<caseNum>" 
					Given User observes ( "<dbType>" ) the "Vote_Information" panel displays.   This should only display if the referral requires voting 
					Then User verifies that the judges' initials in the "<panel>" are sorted based on panel_to_judge.pj_judge_order or by judge.ju_seniority_sort, "<caseNum>", "<cmr_ju_pe_id>", "<cmr_cyv_code>", 
						|courtId|
						|test   |
						
						
					Examples: 
						|server         |refCategory  | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|panel    |
						|CMKA           |PETITION     | 20-42410|CMKA  | 83429         |32          |prhr        |VoteInfo |
						
						
						@AMB-2535 
						Scenario Outline: 
						
							Given I am logged into Briefcase 
								|environment    |userName| password |courtId|
								|Integration    |s haenni| Test2025!|test   |
							Then I select a user 
								|userType   |personrole        |jud          |
								|judge      |Appellate Judges  |Colloton     |
								
							Then User selects "<refCategory>" and "<caseNum>" 
							Then User  selects action using "3155"  and verifies the name of the action displays in the dark blue banner 
								|courtId|
								|test   |
							Then user selects the "View Votes" button next to the relief. User verifies  a popup displays.  In the red banner, the relief they are voting , "CMKA" , "37407" 
							Then User verifies that the judges' initials in the "<panel>" are sorted based on panel_to_judge.pj_judge_order or by judge.ju_seniority_sort, "<caseNum>", "<cmr_ju_pe_id>", "<cmr_cyv_code>", 
								|courtId|
								|test   |
								
								
							Examples: 
								|server         |refCategory  | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|panel    |
								|CMKA           |PETITION     | 20-42410|CMKA  | 83429         |32          |prhr        |viewVotes|
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
		