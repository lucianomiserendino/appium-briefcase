Feature: Display Actions Panel and actions 

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
	
	
	
@smoke @AMB-1038 @AMB-3967 @AMB-1204 @Regression
Scenario: 
	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       Verify Actions are not displayed when briefcaseCtAdmDkt is set to "n"
       

	
	And User verifies the correct "Actions" display for the selected referral 
	
		|courtId|jud     |
		|test   |test    |
				
		

@AMB-2785	
Scenario: The mbr docWP action should be displayed when the me_cav_code is set to 'judgement'     
	
	Then User verifies that Action displays if the assignment type specified in mbr_event record is = judge only

				
		
		
		
		
		
		
		
		