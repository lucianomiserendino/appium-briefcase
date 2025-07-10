@smoke @AMB-1010 @Regression @updated
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
		
   Then the Date Down Arrow should be selected by default
    And the referrals should be sorted by referred date in descending order (newest first)

    When the user clicks the Date Up Arrow
    Then the referrals should be sorted by referred date in ascending order (oldest first)

    When the user clicks the Case Down Arrow
    Then the referrals should be sorted by case number in descending order

    When the user clicks the Case Up Arrow
    Then the referrals should be sorted by case number in ascending order
