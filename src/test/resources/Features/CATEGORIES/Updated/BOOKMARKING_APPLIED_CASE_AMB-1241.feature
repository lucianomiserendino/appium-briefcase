@Regression @AMB-1241 @smoke
Feature: Applied Cases link 


Scenario: 
	Display Applied Cases link  when bookmarking case/referral
	

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
	 
	 Then User verifies there's no bookmark icon displays in the navigation and on the dashboard 
	
	Then User verifies that bookmarking a targeted case doesn't remove the applied link icon
	
	#briefcaseTargetOnly ='y'