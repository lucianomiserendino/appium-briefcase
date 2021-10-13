@AMB @AMB-2574
Feature: As a chamber's user, I should be able to see the vote closing date for a referral so 
	that I know how long until my vote is requested. 

 
Scenario Outline: 
	This task is to verify if there is a vote closing date for a referral, Briefcase displays "Vote Closing:" and the
 vote closing date in bold font under the filed date

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
		
	Then User selects "<refCategory>" and "<caseNum>" 
	Given User observes ( "<dbType>" ) the "Vote_Information" panel displays.   This should only display if the referral requires voting 
	Then If there is a vote closing date for a referral, user verifies that Briefcase displays Vote Closing: and the vote closing date in bold font under the filed date, using  "<ccr_id>" 
		|courtId|
		|test   |
	Examples: 
		|server         |refCategory  | caseNum  |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
		|CMKA           |PETITION     | 20-42410 |CMKA  | 82226         |32          |prhr        |37407 |
		
		