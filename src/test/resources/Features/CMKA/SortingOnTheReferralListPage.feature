Feature: Sorting on the Referral List Page 

Background: 

	Given  User Navigates to environment 
	When  User enters Credentials to Login 
		|userName			|password	|
		|chambers courtney  |Test2020!  |
		
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	
	

@AMB_1010 
Scenario Outline: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 
	

	Given  user selects a server "Appellate DC Development - CMKA" 
	Given User selects a judge and then the motions/petitions category 
	When User selects the sort button, clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in descending order (newest first). 
	And  User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first). 
	Then User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order 
	Then  User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order. 
	
	Examples: 
		|server|
		#|Appellate DC Development - CM5A|
		|Appellate DC Development - CMKA|
		
		
