Feature:  actions 

Background:


		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
   Then User selects a random category
			
   Then User selects a random case
	
	Then User gets the si_value from the site table
	|si_value    |courtId    |
	|ctAdminDkt  |test       |
	
	Then User expands/collapse panel
	
	
		

@AMB-2785	
Scenario: The mbr docWP action should be displayed when the me_cav_code is set to 'judgement'     
	
	Then User verifies that Action displays if the assignment type specified in mbr_event record is = judge only

				
		
		
		
		
		
		
		
		