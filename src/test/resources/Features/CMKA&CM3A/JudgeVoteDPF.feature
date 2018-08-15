Feature: JudgeVote DPF UI 



@AMB_1097 
Scenario Outline: 

	Given user selects the "<category>" and "<case>" 
	When  user  selects action "Vote - testing note permissions" , user verifies the name of the action , "Enter Vote",a toggle under the Enter Vote banner and the label "Apply ruling to all reliefs"  displays 
	Then user  verifies the filers information,the relief text, judge's current vote displays, logged in judge's initials using "<cmr_ju_pe_id>" , "<cmr_cs_caseid>" , "<cmr_cyv_code>" , "<ccr_id>" .If there is not a current vote, the text "No Vote" displays 
	#Then user  verifies next to the logged in judge's vote, a button displays entitled "View Votes" 
	#Then user  verifies a note icon displays next to the "View Votes" button. 
	#Then user  verifies a green Submit button displays at the bottom of the page. 
	
	Examples: 
		|environment   |userName      |password  |server                            |category                | case  |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
		|Integration   |judge werner  |Test2018! |Appellate DC Development - CMKA   |Petitions for Rehearing |15-2594|CMKA  | 81452         |34          |rhr        |34867 |
		
		@AMB_1100
		@AMB_1103 
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
			Given user selects the "<category>" and "<case>" 
			When select  action "<action>" 
			Then user selects the "View Votes" button next to the relief. User verifies  a popup displays.  In the red banner, the relief they are voting , "<dbtype>" , "<ccr_id>" 
			And user verifies each judges' initials to whom the referral was sent , as well as their vote and date they voted, "<dbtype>" , "<ccr_id>" 
			Then user selects a vote and adds notes to a vote
			
			
			Examples: 
				|environment   |userName        |password  |server                                     |category                | case     |action               | dbtype| ccr_id |
				|Integration   |judge werner    |Test2018! |Appellate DC Development - CMKA            |Test Automation         |15-2594   |auto - judgeVote UI  | cmka  |35683   | 
				#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Rehearing Petition      |11-1012   |Vote - mbr           |cm3a   |2973    |
				
				
				
				
				
