@Regression @AMB-1241 
Feature: Applied Cases link 


Scenario: 
	Display Applied Cases link  when bookmarking case/referral
		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
	 
	 Then User verifies there's no bookmark icon displays in the navigation and on the dashboard 
	
	Then User verifies that bookmarking a targeted case doesn't remove the applied link icon
	
	#make sure the site table variable briefcaseTargetOnly ='y'