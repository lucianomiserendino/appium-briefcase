@Smoke @AMB-3510
Feature: Staff Attorney Dashboard - Icons



Scenario: 
	Verify the icons displayed on the dashboard page and navigation are stored in the sfa_briefcase_cat_icon field
	
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |stf  |
		|stf        |Staff Attorneys   |test |
	
	Given User verifies correct assignment categories  display on the dashboard for Staff Attorneys
		
	Then I verify the icons displayed on the dashboard page and navigation are stored in the sfa_briefcase_cat_icon field

