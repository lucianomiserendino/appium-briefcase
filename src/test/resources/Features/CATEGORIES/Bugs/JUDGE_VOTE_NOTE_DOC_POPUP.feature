@Regression @AMB-3245 @smoke
Feature: JudgeVote note/doc popup

#dependent on AMB-4971
Scenario: 
In the scenario where a description hasn't been provided the judgeVote note/doc popup:
1.8 -- Briefcase uses dm_filename in filename if dm_description null/empty
1.7 -- it uses static "Document" if no description is found in document.dm_description
Reference # AMB-3245	


		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	   Then User selects a case that has at least one document In the Note/Vote
       Then User expands/collapse panel
	    Given User observes the Vote Information panel displays.   This should only display if the referral requires voting 
       Then User verifies that doc popup is accessible from judgeVote note and document description is correct
