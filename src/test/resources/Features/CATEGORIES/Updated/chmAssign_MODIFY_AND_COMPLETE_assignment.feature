Feature: chmAssign 

@AMB-3966 @smoke
Scenario: 
The user is modifying a chambers assignment in the chmAssign DPF, ultimately to complete the assignment.  
If they edit any of the previously existing dates during this transaction those changes should be saved, not only the completed date.

       
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
			
   Then User selects a random category
			
   Then User selects a random case
				
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
		

	  