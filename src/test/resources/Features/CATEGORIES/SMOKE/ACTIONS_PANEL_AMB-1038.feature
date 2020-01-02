@AMB @Smoke @AMB-1038 
Feature: Display Actions Panel and actions 


Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.
       

	Given user is logged into Briefcase 
		|environment|userName|password |courtId|
		|Integration|s haenni|Test2022!|CMKA  |
		
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "MOTIONS_PETITIONS" and "15-3314" 
	And User verifies the correct "Actions" display for the selected referral ,using  "CMKA"  and "2310499" 
	
	
	
	
	
	
	
	
	
		