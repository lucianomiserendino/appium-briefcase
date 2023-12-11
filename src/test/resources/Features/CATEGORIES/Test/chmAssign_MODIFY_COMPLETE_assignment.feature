Feature: chmAssign 

@AMB-3966
Scenario: 
The user is modifying a chambers assignment in the chmAssign DPF, ultimately to complete the assignment.  
If they edit any of the previously existing dates during this transaction those changes should be saved, not only the completed date.

       
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
				
	Then User selects action
	|dpf       |courtId    |
	|chmAssign |test       |
	
	 Then user  verifies that briefcase events include the chmAssign DPF
		|caseNumber|courtId|
		|test      |test   |
		
		And User creates a new staff assignment
		|courtId|
		|test   |
	
		
	Then User modifies the existing dates and completes the assignment in the same transaction
		

	  