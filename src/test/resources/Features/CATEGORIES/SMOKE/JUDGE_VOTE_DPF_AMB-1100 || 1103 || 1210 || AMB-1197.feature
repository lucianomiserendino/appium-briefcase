#@AMB 
Feature: JudgeVote DPF UI 


Background: 

	Given user is logged into Briefcase 
		|environment|userName    |password |server|
		|INTEGRATION|judge werner|Test2020!|CMKA  |
@Smoke 
@AMB-1100 
Scenario: 
	In the judgeVote DPF, when the user selects the View Votes button, a popup should display with each judge's vote and the day they voted.
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User  selects action using dbType "CMKA" and  "3142"  and verifies the name of the action displays in the dark blue banner 
	
	Then user selects the "View Votes" button next to the relief. User verifies  a popup displays.  In the red banner, the relief they are voting , "CMKA" , "35683" 
	And User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted. Use "CMKA" , "35683" 
	#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Rehearing Petition      |11-1012      |CM3A   |2973    | 4255|
	
@Smoke 
@AMB-1103 
@AMB-1210 
Scenario: 
	The judgeVote DPF enables judges to add notes to a vote.  There is a parameter in the judgeVote DPF called Note History.  
	If the parameter is set to 'y', the text of the previous vote note (if there is one), should display when the judge adds a 
	note to a new vote.  To the judge, it appears that he/she is just editing an existing vote, even though CM/ECF is creating a new note. 
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User  selects action using dbType "CMKA" and  "3142"  and verifies the name of the action displays in the dark blue banner 
	Then user selects a vote and adds notes to a vote. Use  db "CMKA" ,ccrID "35683" , elID  "3142" , and dpf "judgeVote" 
	And User verifies judge's vote is updated in Vote Information Panel. Use  db "CMKA" ,ccrID "35683" 
	
	
@AMB-1097 
Scenario Outline: 
#has to be changed to PETITIONS_FOR_REHEARING, 3124
	Then User selects "<refCategory>" and "<caseNum>" 
	Then User  selects action using dbType "<dbType>" and  "<el_id>"  and verifies the name of the action displays in the dark blue banner 
	Then   for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use "<dbType>", "<cmr_ju_pe_id>" , "<cmr_cs_caseid>" ,"<cmr_cyv_code>" , "<ccr_id>" . 
	Then  User checks each judge's vote  and the date  displays under their initials, "<dbType>" using  "<ccr_id>" 
	Examples: 
		|el_id | refCategory             | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
		|3142  | TEST_AUTOMATION         | 15-2594  |CMKA | 81452         |34          |autotst     |35683 |
		
		
	