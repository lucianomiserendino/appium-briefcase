@Smoke @AMB-1010 @k
Feature: Sorting on the Referral List Page 


Scenario: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 
	
	
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
	
	Then I select a user 
		|userType   |personrole        |jud          |
		|judge      |Appellate Judges  |test         |
	Then User selects random judge category
		|courtId|
		|test   |
		
	Then User verify the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order (newest first). 
	Then User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first). 
	And User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order 
	Then User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in ascending order. 

		
		
