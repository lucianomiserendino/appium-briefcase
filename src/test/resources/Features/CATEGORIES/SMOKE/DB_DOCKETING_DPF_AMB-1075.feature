@AMB @Smoke @AMB-1075 
Feature: note DPF back-end updates 

Scenario: 
	Adding a note back-end database updates.
            
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "TEST_AUTOMATION" and "15-2594" 
	Then User selecs action, enters a comment in the editable field, submits and verifies Db "CMKA" is updated correctly, ( use "15-2594"  and "32" ) 
	
		|el_list_text  | dm_acc_crt | dm_acc_ctlink  | dm_acc_spec |
		|3060         |     y      |      n         |      n      |
		|3070         |     n      |      y         |      n      |
		|3128         |     n      |      n         |      y      |
		|3118         |     n      |      n         |      y      |
		|3074         |     n      |      n         |      y      |
		|3127         |     n      |      n         |      y      |
		|3072         |     n      |      n         |      y      |
		
		
		
		
		
		