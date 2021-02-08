Feature: Pending Tasks --Display date assignment type 


@Regression 
@AMB-1220 
Scenario Outline: 

		

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
		
	When User selects a  "Pending Tasks" 
	Then User verifies each assignment display the most recent date type by using following info: dbType "<dbType>", "<caseNumber>", "<peId>", "<cmr_cyv_code>", "<pr_last_name>", "pr_first_name" 
	Examples: 
	
		|courtId|caseNumber|peId|cmr_cyv_code|pr_last_name|pr_first_name|
		|CMKA   |14-2283   |32  |noargcs     |Colloton    |Steven       |