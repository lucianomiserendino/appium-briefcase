@AMB-3459
Feature: Assignments are removed from Pending Tasks when 'term' selected



Scenario: Verify assignments are removed from the Pending Tasks folder when the user runs a chmSilentAssign action with any of 
the "term" selections as the mode.
       
		#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
	
	Then I select a user 
		|userType   |personrole        |jud          |
		|judge      |Appellate Judges  |test         |
		
	When User selects a  "Pending Tasks" 
	And User select a sub folder
	Then selects a random pending tasks case
	Then User expands/collapse panel
	#Then User verifies assignments are removed from the Pending Tasks folder when a chmSilentAssign action is ran with any of the "term" selections as the mode. 