@AMB @Regression  
Feature: Site Table variable to enable court admins to docket in Briefcase 


@AMB-1204
Scenario: 
		Given User sets the "briefcaseCtAdminDkt" site var to "y"
	|courtId|userName    | password |environment|
	|test   |s haenni    | Test2024!|Integration|
	
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	
		Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User  selects action using "3142"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
	Then user selects a vote and adds notes to a vote. Use  db "CMKA" ,ccrID "35683" , elID  "3142" , and dpf "judgeVote" 
	And User verifies judge's vote is updated in Vote Information Panel. Use  db "CMKA" ,ccrID "35683" 
	
	
	
	
Scenario: 
		Given User sets the "briefcaseCtAdminDkt" site var to "n"
	|courtId|userName    | password |environment|
	|test   |s haenni    | Test2024!|Integration|
	
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	
		Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User  selects action using "3142"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
	Then user selects a vote and adds notes to a vote. Use  db "CMKA" ,ccrID "35683" , elID  "3142" , and dpf "judgeVote" 
	And User verifies judge's vote is updated in Vote Information Panel. Use  db "CMKA" ,ccrID "35683" 	
	
	Given User sets the "briefcaseCtAdminDkt" site var to "y"
	|courtId|userName    | password |environment|
	|test   |s haenni    | Test2024!|Integration|
	
	

