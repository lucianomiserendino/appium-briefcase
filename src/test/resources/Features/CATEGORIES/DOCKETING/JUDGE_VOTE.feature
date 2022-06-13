@AMB-2534 @j
Feature:  Judge initials order in Vote Information panel	
Scenario Outline: The mbr docWP action should be displayed when the me_cav_code is set to 'judgement'     
  
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
					Then User verifies that the judges' initials in the "<panel>" are sorted based on panel_to_judge.pj_judge_order or by judge.ju_seniority_sort, "<caseNum>", "<cmr_ju_pe_id>", "<cmr_cyv_code>", 
						|courtId|
						|test   |
						
						
					Examples: 
						|server         |refCategory  | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|panel    |
						|CMKA           |PETITION     | 20-42410|CMKA  | 83429         |32          |prhr        |VoteInfo |
						
						