@AMB-1234 
Feature: Docket Entries for Chambers Users 

Scenario: 

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |Benton  |
	Then user gets the entries of the judge ( "21-3877" ) and logs out 
	
		Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |JAHaenni| Test2026!|test   |
		
	Then user verifies a JA or law clerk can see the same entries as their judge  ( "21-3877" ) 
	
	