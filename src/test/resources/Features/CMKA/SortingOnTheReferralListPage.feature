Feature: Sorting on the Referral List Page 


@Smoke
@AMB_1010 
Scenario Outline: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 
	

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects Judge,  "<refCategory>" 
	#When User selects the sort button, clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in descending order (newest first). User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first). 
	
	Then User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order . User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order. 
	
	Examples: 
		|environment   |userName          |password  |server                                   |refCategory       |
		|Integration   |chambers courtney |Test2020!|Appellate DC Development - CMKA           |Motions/Petitions |
		|Testing       |judgewilliams   |Testpass1! |Test2017!|Appellate DC Installation Testing - CM3A  |Motion/Petition   | 
		
		
		
		
