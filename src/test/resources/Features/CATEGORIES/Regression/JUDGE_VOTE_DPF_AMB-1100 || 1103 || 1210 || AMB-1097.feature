@Regression @Smoke 
Feature: JudgeVote DPF UI 
#related to AMB-1097 as well
Background: 

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
			
	  Then User selects action
	  |dpf       |courtId    |
	  |judgeVote |test       |
	  
		
@AMB-1100		
Scenario: 
	In the judgeVote DPF, when the user selects the View Votes button, a popup should display with each judge's vote and the day they voted.
	
			
		
	Then user verifies a popup displays.  In the red banner, the relief they are voting on should display 

		
	And User verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted

		
		
		
		
		
#20-42410
@AMB-1103 
@AMB-1210 
Scenario: 
	The judgeVote DPF enables judges to add notes to a vote.  There is a parameter in the judgeVote DPF called Note History.  
	If the parameter is set to 'y', the text of the previous vote note (if there is one), should display when the judge adds a 
	note to a new vote.  To the judge, it appears that he/she is just editing an existing vote, even though CM/ECF is creating a new note. 

		
	Then user selects a vote and adds notes to a vote. 

	And User verifies judge's vote is updated in Vote Information Panel.
		
	
						
						
						
	@AMB-2535 
	Scenario:     
	 
    Then User verifies that View Votes button is displayed next to the logged in judge’s vote 
		
     When the button is tapped, User verifies that it shows all the judges’ vote in a popup.


							#Then user selects the "View Votes" button next to the relief. User verifies  a popup displays.  In the red banner, the relief they are voting , "CMKA" , "37407" 
						#	Then User verifies that the judges' initials in the "<panel>" are sorted based on panel_to_judge.pj_judge_order or by judge.ju_seniority_sort, "<caseNum>", "<cmr_ju_pe_id>", "<cmr_cyv_code>", 
							#	|courtId|
							#	|test   |
								
								
							#Examples: 
							#	|server         |refCategory  | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|panel    |
							#	|CMKA           |PETITION     | 20-42410|CMKA  | 83429         |32          |prhr        |viewVotes|
								
								
	
	

	