Feature: Pending Tasks --Display date assignment type 


@Regression 
@AMB-1220 
Scenario Outline: 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "judge werner" and "Test2020!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	When User selects a  "Pending Tasks" 
	Then User verifies each assignment display the most recent date type by using following info: dbType "<dbType>", "<caseNumber>", "<peId>", "<cmr_cyv_code>", "<pr_last_name>", "pr_first_name" 
	Examples: 
	
		|dbType|caseNumber|peId|cmr_cyv_code|pr_last_name|pr_first_name|
		|CMKA  |14-2283   |34  |noargcs     |Benton      |Duane        |