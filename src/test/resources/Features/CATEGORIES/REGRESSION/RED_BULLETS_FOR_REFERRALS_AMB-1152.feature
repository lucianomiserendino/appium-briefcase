@AMB @Regression  
Feature: Red bullet displays for new referrals and does not display for viewed referrals 


@AMB-1152
Scenario: 
	A red bullet icon displays next to referrals which the user has not viewed yet. 
	 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. 
	  This task is to automate the display of the red bullet icon.

	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	# Verifies the red bullet is removed indicating the referral has been viewed 
	Then User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page 
	Then User closes and reopens the app
  Then User closes the app, reopens and goes back to the category that contains the referral that was just viewed 
#	And User logs out from the Briefcase
	
#	Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
	#Then I select a user 
	#	|userType   |personrole        |jud     |
	#	|judge      |Appellate Judges  |test    |
	
	#Then User goes back to the "<refCategory>" that contains the referral that was just viewed and verify the bullet does not display 
	
	#Examples: 
		#|courtId  |refCategory       |
		#|CMKA     |Motion            |
		
		
		
		

		
		
		
		
		
		
		
		

		