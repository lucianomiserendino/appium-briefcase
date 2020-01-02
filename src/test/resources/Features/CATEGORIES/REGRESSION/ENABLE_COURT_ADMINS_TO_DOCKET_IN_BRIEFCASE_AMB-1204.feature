@AMB @Regression @AMB-1204 
Feature: Site Table variable to enable court admins to docket in Briefcase 



Scenario: 
	Given User sets the "briefcaseCtAdminDkt" site var to "y" on "CMKA" 
	Given user is logged into Briefcase 
		|environment|userName|password |server|
		|INTEGRATION|s haenni|Test2022!|CMKA  |
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User  selects action using dbType "CMKA" and  "3142"  and verifies the name of the action displays in the dark blue banner 
	Then user selects a vote and adds notes to a vote. Use  db "CMKA" ,ccrID "35683" , elID  "3142" , and dpf "judgeVote" 
	And User verifies judge's vote is updated in Vote Information Panel. Use  db "CMKA" ,ccrID "35683" 
	
	
	
	
Scenario: 
	Given User sets the "briefcaseCtAdminDkt" site var to "n" on "CMKA" 
	Given user is logged into Briefcase 
		|environment|userName|password |server|
		|INTEGRATION|s haenni|Test2022!|CMKA  |
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User verifies the court admin doesn't have access to select Actions in Briefcase 
	Given User sets the "briefcaseCtAdminDkt" site var to "y" on "CMKA" 
