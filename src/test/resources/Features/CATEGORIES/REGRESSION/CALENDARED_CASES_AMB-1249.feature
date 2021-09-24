@Regression @AMB-1249
Feature: Display correct days for calendared cases 

Scenario: 
	Verify  that days  for calendared cases are displayed correctly
	
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
	    |Integration    |s haenni| Test2025!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	When User selects a  "Cases on" 
	Then User selects a session and verifies days are displayed corrcetly in that session, judge's peID is "32"
	|courtId|
	|test   |
	
	
