@Regression @AMB-1075
Feature: note DPF back-end updates 

Scenario: 
	Adding a note back-end database updates.
            
	  Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user 
		|userType   |personrole        |jud       |
		|judge      |Appellate Judges  |test      |
	
	#Then User selects "TEST_AUTOMATION" and "21-3877" 
	
	Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	
	Then User selecs action, enters a comment in the editable field, submits and verifies Db "CMKA" is updated correctly, ( use "21-3877"  and "32" ) 
	
		|el_list_text  | dm_acc_crt | dm_acc_ctlink  | dm_acc_spec |
		|3060         |     y      |      n         |      n      |
		|3070         |     n      |      y         |      n      |
		|3128         |     n      |      n         |      y      |
		|3118         |     n      |      n         |      y      |
		|3074         |     n      |      n         |      y      |
		|3127         |     n      |      n         |      y      |
		|3072         |     n      |      n         |      y      |
		
		
		
		
		
		