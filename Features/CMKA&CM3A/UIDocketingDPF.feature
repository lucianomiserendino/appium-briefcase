Feature: note DPF UI 




@Smoke
@AMB_1055 
Scenario Outline: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Given User selects category "<refCategory>" and "<caseNume>" 
	When User expands the Actions panel, selects an action from "<dbType>" using "<el_id>"
	Then User verifies the name of the action displays in the dark blue banner,use  "<dbType>" and  "<el_id>". 
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	And user verifies an editable "Description" , "Comment" , "Submit" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF ("<dbType>" and "<el_id>")
	
	Examples: 
		|environment   |userName          |password  |server                                   |refCategory       | caseNume |dbType| el_id|
		|Integration   |chambers courtney |Test2020!|Appellate DC Development - CMKA           |Motions/Petitions | 15-3314  |CMKA  |3060  |
		|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |Motion/Petition   | 12-6627  |CM3A  |4260  |
		
		
		
    