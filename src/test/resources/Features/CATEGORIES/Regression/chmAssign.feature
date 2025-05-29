Feature: chmAssign

@Regression @smoke @AMB-1122 @AMB-1123 @AMB-1137 @AMB-1170 @AMB-1173 
Scenario: 
	This task is to verify that a chambers user is able to create a new staff assignment,
	to verify back-end updates when a new staff assignment is created,
	edit existing staff assignments and verify Back-end after modifying assignment 

       
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |judge   |
			
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
		
	Then User edits existing staff assignment
		|courtId|
	  	|test   |
	    
	   #	 And User terminates the assignment
   		# |courtId|
	   	# |test   |
	    
	   # Then User navigates to View Case Info, then taps Docket Entries
	   #	 |Auto Test|
	    
	   	# Then User verifies that Briefcase supports the chmAssignText TPF
   