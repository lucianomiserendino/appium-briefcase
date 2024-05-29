@AMB-2607
Feature: Chambers users are able to download/view annotated documents 

 
Scenario Outline:
Verify the chambers users are able to view/open Judges' annotated documents 

	Given User is logged into Briefcase,   "<environment>",  "<userName>",  "<password>",  "<courtId>"		
	Then User selects "PETITION" and "20-42410" 
	And User observes annotated documents have a green plus next to them and taps on it to expand the original and annotated documents 


Examples:
		|environment    |userName    | password |courtId|
		|Integration    |Judge Werner| Test2026!|CMKA   |
		|Integration    |JAHaenni    | Test2026!|CMKA   |
		
		
		
		
		
		
		
		
		
		
		
		