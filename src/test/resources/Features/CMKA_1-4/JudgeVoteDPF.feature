Feature: JudgeVote DPF UI 





@AMB-1100 
@AMB-1103 
Scenario Outline: 
	In the judgeVote DPF, when the user selects the View Votes button, a popup should display with each judge's vote and the day they voted.
			The judgeVote DPF enables judges to add notes to a vote. 
			 There is a parameter in the judgeVote DPF called Note History.  
			 If the parameter is set to 'y', the text of the previous vote note (if there is one), 
			 should display when the judge adds a note to a new vote. 
			  To the judge, it appears that he/she is just editing an existing vote, even though CM/ECF is creating a new note. 
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects Judge,  "<category>" and  "<caseNum>" 
	Then User  selects action using dbType "<dbType>" and  "<el_id>"  and verifies the name of the action displays in the dark blue banner 
	Then user selects the "View Votes" button next to the relief. User verifies  a popup displays.  In the red banner, the relief they are voting , "<dbType>" , "<ccr_id>" 
	And user verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted, "<dbType>" , "<ccr_id>" 
	Then user selects a vote and adds notes to a vote. Use  db "<dbType>" ,ccrID "<ccr_id>" , elID  "<el_id>" 
	#Then user verifies a note icon displays on the left of a judge's vote 
	#Then user verifies tapping on the note icon will display the note in a popup
	#Then user verifies the title of the popup should be the document.dm description of the note note and the date it was created
	#Then user verifies the text of the note display in the section of the popup
	
	
	
	Examples: 
		|environment   |userName        |password  |server                                     |category                | caseNum     | dbType| ccr_id |el_id|
		|Integration   |judge werner    |Test2019! |Appellate DC Development - CMKA            |Test Automation         |15-2594      | CMKA  |35683   | 3142|
		#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Rehearing Petition      |11-1012      |CM3A   |2973    | 4255|
		
		
		
		
		
