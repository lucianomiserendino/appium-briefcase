@AMB @Smoke @AMB-1055 
Feature: note DPF UI 




Scenario Outline: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.

	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
		
	Then User selects "<category>" and "<caseNum>" 
	Then User  selects action using dbType "<dbType>" and  "<el_id>"  and verifies the name of the action displays in the dark blue banner 
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	And user verifies an editable "Description" , "Comment" , "Submit" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF ("<dbType>" , "<dpfName>", "<el_id>") 
	
	Examples: 
		|category          | caseNum |dbType| el_id| dpfName|
		|MOTIONS_PETITIONS | 15-3703 |CMKA  |3153  |note    |
		
		
		
    