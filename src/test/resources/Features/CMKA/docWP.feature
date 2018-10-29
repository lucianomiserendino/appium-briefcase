Feature: docWP Optional and docWP Mandatory 



Background: 
	Given  User Navigates to  "Integration" environment 
	When   User enters Credentials to Login "judge werner" and "Test2019!" 
	And  User clicks on Send Key to Device 
	Then  User navigates to MobileBrifcase App 
	And   user selects a "Appellate DC Development - CMKA" 
	
@AMB-1083 
Scenario Outline: 
	Verify when an action is selected that contains the docWP DPF and the docWP DPF UI displays
   
	When User selects Judge,  "<category>" and  "<caseNum>" 
	Then User  selects action in "<dbType>" and verifies the name of the action displays in the dark blue banner 
		|el_id|
		|3135 |
		|3136 |
	And User verifies  the text "Upload Documents", "not selected", "Enter Description" , "Select File" , "Remove File", "Submit" display on the page 
	
	Examples: 
		|category        |caseNum  |dbType  |
		|Test Automation |18-12418 | CMKA   |
		
		