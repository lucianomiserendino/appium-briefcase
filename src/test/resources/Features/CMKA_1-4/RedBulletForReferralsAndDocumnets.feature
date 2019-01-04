Feature: Red bullet displays for new referrals and does not display for viewed referrals 



@AMB-1152 
Scenario: 
	A red bullet icon displays next to referrals which the user has not viewed yet. 
	 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. 
	  This task is to automate the display of the red bullet icon.


	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "judge werner" and "Test2019!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	When User selects a  "Motions/Petitions" 
	Then User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals 
	And User taps on a referral and then gets back to the referral list page.  Verifies the red bullet is removed indicating the referral has been viewed 
	Then User closes the app and reopen and go back to the category that contains the referral that was just viewed 
	#Then User selects "Briefs"  and clicks on "Autosync doc" 
	#And User closes Briefcase app, opens back and verifies the red bullet does not display for viewed referrals/documnets "Autosync doc". Then User logs out. 
	#Given  User Navigates to  "Integration" environment 
	#When   User enters Credentials to Login "judge werner" and "Test2019!" 
	#And  User clicks on Send Key to Device 
	#Then  User navigates to MobileBrifcase App 
	#And   user selects a "Appellate DC Development - CMKA" 
	#When User selects a Judge, category: "Test Automation", created case and verifies Red Bullet for previously viewed Referral and Documnet "Autosync doc" doesn't display 
	
	