@Regression
Feature: Pending Tasks --Display date assignment type 


@AMB-1220 @smoke
Scenario: 


		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	When User selects a  "Pending Tasks" 
	Then User verifies each assignment display the most recent date type 
