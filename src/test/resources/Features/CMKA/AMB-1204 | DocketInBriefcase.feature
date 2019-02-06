Feature: Site Table variable to enable court admins to docket in Briefcase 

@AMB-1204 
Scenario: 
	As a court admin, I want the option to enter judge's votes in Briefcase.
 
	#Scenario 1 : 
	Given User sets the value of the site table variable in "CMKA" : briefcaseCtAdminDkt  to "y" 
	Given  User Navigates to  "INTEGRATION" environment 
	When   User enters Credentials to Login "s haenni" and "Test2021!" 
	And  User clicks on Send Key to Device 
	Then  User navigates to MobileBrifcase App 
	And   user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User  selects action using dbType "CMKA" and  "3142"  and verifies the name of the action displays in the dark blue banner 
	Then user selects a vote and adds notes to a vote. Use  db "CMKA" ,ccrID "35683" , elID  "3142" 
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	And User verifies judge's vote is updated in Vote Information Panel. Use  db "CMKA" ,ccrID "35683" 
	
	
	#Scenario 2 : 
	#If the value of the site table variable "briefcaseCtAdminDkt" is set to 'n' : 
	#1. Repeat step 1 in scenario 1. 
	#2. Select the Test Automation Category and case 15-2594. 
	#3. Verify the court admin doesn't have access to select Actions in Briefcase 
 