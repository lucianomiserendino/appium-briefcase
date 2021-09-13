Feature: Sorting on the Referral List Page 

@Smoke @AMB-1010
Scenario Outline: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 
	
	
	Given I am logged into Briefcase 
	|environment    |userName| password |courtId|
	|Integration    |s haenni| Test2025!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Benton       |
		
	When User selects a  "<refCategory>" 
	Then User verify the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order (newest first). 
	Then User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first). 
	And User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order 
	Then User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in ascending order. 
	
	Examples: 
		|server         |refCategory       |
		|CMKA           |Application       |
		
		
		
@cm3a
Scenario Outline: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 
	
	
	Given I am logged into Briefcase 
		|environment    |userName       | password |courtId|
		|Testing        |sysadmin haenni| Test2022!|test   |
		
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Williams     |
		
	When User selects a  "<refCategory>" 
	Then User verify the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order (newest first). 
	Then User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first). 
	And User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order 
	Then User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in ascending order. 
	
	Examples: 
		|server         |refCategory |
		|CM3A           |Screening   |
		
		