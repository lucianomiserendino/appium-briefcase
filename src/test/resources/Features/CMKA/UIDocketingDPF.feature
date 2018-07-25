Feature: note DPF UI 

Background: 
 	
	Given  User Navigates to environment 
	When  User enters Credentials to Login 
		|userName			|password	|
		|chambers courtney  | Test2020! |
		
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	
	
	

@AMB_1055 
Scenario: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.
	Given  user selects a "<server>" 
	Given User selects Motions/Petitions "15-3703" 
	When User expands the Actions panel, selects an action 
	Then User verifies the name of the action displays in the dark blue banner. 
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	And user verifies an editable "Description", "Comment", "Submit" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF 
	
	
    