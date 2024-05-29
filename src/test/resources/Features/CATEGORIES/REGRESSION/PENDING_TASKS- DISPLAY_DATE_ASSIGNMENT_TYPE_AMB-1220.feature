Feature: Pending Tasks --Display date assignment type 


@AMB-1220 
Scenario Outline: 

		

	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
		
	When User selects a  "Pending Tasks" 
	Then User verifies each assignment display the most recent date type by using following info: dbType "<dbType>", "<caseNumber>", "<peId>", "<cmr_cyv_code>", "<pr_last_name>", "pr_first_name" 
	Examples: 
	
		|courtId|caseNumber|peId|cmr_cyv_code|pr_last_name|pr_first_name|
		|CMKA   |14-2283   |32  |noargcs     |Colloton    |Steven       |