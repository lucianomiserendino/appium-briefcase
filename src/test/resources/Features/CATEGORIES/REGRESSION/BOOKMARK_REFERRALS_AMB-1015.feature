@Regression @AMB-1015
Feature: Bookmark functionality 

Scenario: 
	There is functionality in Briefcase that enables users to bookmark referrals.  
	The following scenarios need to be automated:
	

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
		
	Then User verifies there's no bookmark icon displays in the navigation and on the dashboard 
	When User selects a  "Test Automation" 
	Then user  taps on the bookmark icon next to a case  and verifies the bookmark icon displays in the navigation and on the dashboard page. 
	And user taps on bookmark icon in the navigation or on the dashboard then taps on the bookmark icon next to the case he just bookmarked and verifies the case is removed from the bookmark category 
	
