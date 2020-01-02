@AMB @Regression @AMB-1152 
Feature: Red bullet displays for new referrals and does not display for viewed referrals 



Scenario Outline: 
	A red bullet icon displays next to referrals which the user has not viewed yet. 
	 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. 
	  This task is to automate the display of the red bullet icon.

	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|judge werner     |Test2020!|CMKA  |
	When User selects a  "<refCategory>" 
	Then User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page 
	# Verifies the red bullet is removed indicating the referral has been viewed 
	Then User closes the app and reopen and go back to the "<refCategory>" that contains the referral that was just viewed 
	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|judge werner     |Test2020!|CMKA  |
	Then User goes back to the "<refCategory>" that contains the referral that was just viewed and verify the bullet does not display 
	
	Examples: 
		|server        |refCategory       |
		|CMKA          |Motions/Petitions |
		
		
		
Scenario Outline: 
	A red bullet icon displays next to referrals which the user has not viewed yet. 
	 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. 
	  This task is to automate the display of the red bullet icon.

	Given user is logged into Briefcase 
		|environment|userName              |password |server|
		|INTEGRATION|chambers courtney     |Test2023!|CMKA  |
	When User selects a  "<refCategory>" 
	Then User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page 
	# Verifies the red bullet is removed indicating the referral has been viewed 
	Then User closes the app and reopen and go back to the "<refCategory>" that contains the referral that was just viewed 
	Given user is logged into Briefcase 
		|environment|userName              |password |server|
		|INTEGRATION|chambers courtney     |Test2023!|CMKA  |
	Then User goes back to the "<refCategory>" that contains the referral that was just viewed and verify the bullet does not display 
	Examples: 
		|server        |refCategory       |
		|CMKA          |Pending Tasks     |
		