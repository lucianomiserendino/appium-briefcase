Feature: Display Actions Panel and actions 

@Smoke @AMB-1038 @k
Scenario: 

	If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display, when expanded all the applicable actions should display.
       
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	And User verifies the correct "Actions" display for the selected referral 
	
		|courtId|caseNumber|jud     |
		|test   |test      |test    |
				
		

@AMB-2785	
Scenario: The mbr docWP action should be displayed when the me_cav_code is set to 'judgement'     
  
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
 Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	Then User verifies that Action displays if the assignment type specified in mbr_event record is = judge only

				
		
		
		
		
		
		
		
		