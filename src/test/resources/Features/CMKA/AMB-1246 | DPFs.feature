Feature: Execute multiple DPFs in the same action 



@AMB-1246 
Scenario: 
	This task is to verify the Data is saving when executing multiple DPFs in the same action
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "judge werner" and "Test2019!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then  User selects "PETITIONS_FOR_REHEARING" and "18-12418" 
	Then User  selects action using dbType "CMKA" and  "3116"  and verifies the name of the action displays in the dark blue banner 
	
	