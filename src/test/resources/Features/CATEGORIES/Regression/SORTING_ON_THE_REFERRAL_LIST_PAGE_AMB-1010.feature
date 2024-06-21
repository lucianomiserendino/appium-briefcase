@smoke @AMB-1010 @Regression
Feature: Sorting on the Referral List Page 


Scenario: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 
	
	
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	Then User selects random judge category
		|courtId|
		|test   |
		
	Then User verifies that the Date Down Arrow is selected by default and the referrals are sorted by referred date in descending order (newest first). 
	Then User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first). 
	And User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order 
	Then User clicks on the Case Up Arrow button and verifies the referrals are sorted by case number in ascending order. 

		
		
