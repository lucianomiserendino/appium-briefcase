Feature: Display Actions Panel and actions 


@Smoke 
@AMB_1038 
Scenario Outline: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects Judge,  "<refCategory>" and  "<caseNume>" 
	Then "<dbType>". User verifies "Actions" is diplayed and  expands the Actions panel 
	And User verifies the correct actions display for the selected referral ,using  "<dbType>"  and "<cmr_id>" 
	
	Examples: 
		|environment   |userName          |password  |server                                   |refCategory       | caseNume |dbType| cmr_id |
		|Integration   |chambers courtney |Test2020!|Appellate DC Development - CMKA           |Motions/Petitions | 15-3314  |CMKA  | 2303021|
		|Testing       |Chambers Haenni   |Test2017!|Appellate DC Installation Testing - CM3A  |Motion/Petition   | 12-6627  |CM3A  | 364   |
		
		
		
		
		
		
		
		