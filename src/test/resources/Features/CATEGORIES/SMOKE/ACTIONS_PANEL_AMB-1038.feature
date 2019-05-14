Feature: Display Actions Panel and actions 


@Smoke 
@AMB-1038 
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.
       
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "MOTIONS_PETITIONS" and "15-3314" 
	And User verifies the correct "Actions" display for the selected referral ,using  "CMKA"  and "2310499" 
	
	
	#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Motion/Petition   | 12-6627  |CM3A  | 364   |
	
	
	
	
	
	
	
		