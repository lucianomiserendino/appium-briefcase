Feature: Pending Tasks --Display date assignment type 


@Regression 
@AMB-1220 
Scenario Outline: 
	Given user is logged into Briefcase 
		|environment|userName    |password |server|
		|INTEGRATION|judge werner|Test2020!|CMKA  |
	When User selects a  "Pending Tasks" 
	Then User verifies each assignment display the most recent date type by using following info: dbType "<dbType>", "<caseNumber>", "<peId>", "<cmr_cyv_code>", "<pr_last_name>", "pr_first_name" 
	Examples: 
	
		|dbType|caseNumber|peId|cmr_cyv_code|pr_last_name|pr_first_name|
		|CMKA  |14-2283   |34  |noargcs     |Benton      |Duane        |