@Smoke @AMB-1038
 Feature: Display Actions Panel and actions 


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
				
		

	
		
		
		
		
		
		
		
		