Feature: Display reliefs in Vote Info panel/judgeVote DPF for applied cases 



@CMKA
@AMB-1292 
Scenario Outline: 


	Given On "<dbType>" user sets the si_value of the site var to "<si_value>"  and si_code "<si_code>" 
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User selects "MOTIONS_PETITIONS" and "19-11519"
	Given User observes ( "<dbType>" ) the "Vote Information" panel displays.   This should only display if the referral requires voting  
	
	Examples: 
		|environment   |userName          |password  |server   |caseNum |dbType| si_value| si_code           |
		|INTEGRATION   |judge werner      |Test2019! |CMKA     |19-11519|CMKA  |y        |briefcaseTargetOnly|
		
		
		
		#|INTEGRATION   |s haenni          |Test2021! |CMKA          |Motions/Petitions |Appellate_Judges|Colloton |18-12418|
		