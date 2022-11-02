@Regression @AMB-1249
Feature: Display correct days for calendared cases 

Scenario: 
	Verify  that days  for calendared cases are displayed correctly
	
		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
		
	When User selects a  "Cases on" 
	Then User selects a session and verifies days are displayed corrcetly in that session, judge's peID is "32"
	|courtId|
	|test   |
	
	
