@AMB-2574 @Regression @smoke
Feature: Vote closing date 

 
Scenario: 
	This task is to verify if there is a vote closing date for a referral, Briefcase displays "Vote Closing:" and the
 vote closing date in bold font under the filed date

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
   Then User selects a random category
			
   Then User selects a random case
		
     Given User observes the Vote Information panel displays.   This should only display if the referral requires voting 
	Then If there is a vote closing date for a referral, user verifies that Briefcase displays Vote Closing: and the vote closing date in bold font under the filed date 
		|courtId|
		|test   |
