Feature: note DPF UI 




#@Smoke
@AMB-1055
Scenario Outline: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>"
	Then User selects "<category>" and "<caseNum>" 
	Then User  selects action using dbType "<dbType>" and  "<el_id>"  and verifies the name of the action displays in the dark blue banner 	
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	And user verifies an editable "Description" , "Comment" , "Submit" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF ("<dbType>" and "<el_id>") 
	
	Examples: 
		|environment   |userName          |password  |server         |category          | caseNum |dbType| el_id|
		|INTEGRATION   |chambers courtney |Test2021! |CMKA           |MOTIONS_PETITIONS | 15-3703  |CMKA  |3153  |
		#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Motion/Petition   | 12-6627  |CM3A  |4260  |
		
		
		
    