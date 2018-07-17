Feature: Display Actions Panel and actions 

Background: 

	Given  User Navigates to environment 
	When  User enters Credentials to Login 
		|userName			|password	|
		|chambers courtney  |Test2020!  |
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	
	
	
@Regression_CMKA 
@AMB_1038
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.
	Given  user selects a server "Appellate DC Development - CMKA" 
	When User selects Judge "Colloton"  >> Motions/Petitions >> and anycase 
	Then User verifies "Actions" is diplayed and  expands the Actions panel (CMKA) 
	And User verifies the correct actions display for the selected referral (CMKA) 
	
	
	
@Smoke_CM5A
@AMB_1038_CM5A
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.
	Given  user selects a server "Appellate DC Development - CM5A" 
	When User selects "Williams"  >> Motion/Petition >> and anycase 
	Then User verifies "Actions" is diplayed and  expands the Actions panel (CM5A) 
	And User verifies the correct actions display for the selected referral (CM5A) 
	
	
	
	
	
		