Feature: Red bullet displays for new referrals/documnets and does not display for viewed referrals/documnets 



@AMB-1152 
Scenario: 
	A red bullet icon displays next to referrals/documnets which the user has not
viewed yet. 
Once the user taps on the referral, the red bullet icon is removed, indicating
the referral has been viewed. 
This task is to automate the display of the red bullet icon. 


	Given  User creates a  case 
	Given  User Navigates to  "Integration" environment 
	When   User enters Credentials to Login "judge werner" and "Test2018!" 
	And  User clicks on Send Key to Device 
	Then  User navigates to MobileBrifcase App 
	And   user selects a "Appellate DC Development - CMKA" 
	When User selects a Judge, category: "Test Automation" and created case 
	Then User selects "Briefs"  and clicks on "Autosync doc" 
	And User closes Briefcase app, opens back and verifies the red bullet does not display for viewed referrals/documnets "Autosync doc". Then User logs out.
	Given  User Navigates to  "Integration" environment 
	When   User enters Credentials to Login "judge werner" and "Test2018!" 
	And  User clicks on Send Key to Device 
	Then  User navigates to MobileBrifcase App 
	And   user selects a "Appellate DC Development - CMKA" 
	When User selects a Judge, category: "Test Automation", created case and verifies Red Bullet for previously viewed Referral and Documnet doesn't display
	
	