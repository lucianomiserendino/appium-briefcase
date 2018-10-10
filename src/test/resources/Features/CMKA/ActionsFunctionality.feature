Feature: Display Actions Panel and actions 


 
@AMB-1038 
Scenario Outline: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects Judge,  "<refCategory>" and  "<caseNum>" 
	Then "<dbType>". User verifies "Actions" is diplayed and  expands the Actions panel 
	And User verifies the correct actions display for the selected referral ,using  "<dbType>"  and "<cmr_id>" 
	
	Examples: 
		|environment   |userName          |password  |server                                   |refCategory       | caseNum |dbType| cmr_id |
		|Integration   |chambers courtney |Test2021!|Appellate DC Development - CMKA           |Motions/Petitions | 15-3314  |CMKA  | 2303021|
		#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Motion/Petition   | 12-6627  |CM3A  | 364   |
		
		
		
		
		
		
		
		