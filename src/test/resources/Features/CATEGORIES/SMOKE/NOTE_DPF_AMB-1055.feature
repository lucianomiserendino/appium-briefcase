@Smoke
Feature: note DPF UI 

@AMB-1055 @S 
Scenario Outline: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
			|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
	
	Then User selects "<category>" and "<caseNum>" 
	Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
		
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	And user verifies an editable "Description" , "Comment" , "Submit" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF ("<dbType>" , "<dpfName>", "<el_id>") 
	
	Examples: 
		|category| caseNum |dbType| el_id| dpfName|
		|MOTION  | 15-3703 |CMKA  |3153  |note    |
			

    
    

    