@AMB @Regression  
Feature: Red bullet displays for new referrals and does not display for viewed referrals 


@AMB-1152
Scenario Outline: 
	A red bullet icon displays next to referrals which the user has not viewed yet. 
	 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. 
	  This task is to automate the display of the red bullet icon.

	#Given I am logged into Briefcase 
	#|environment    |userName| password |courtId|
		#|Integration    |s haenni| Test2024!|test   |

	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
	
	When User selects a  "<refCategory>" 
	Then User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page 
	# Verifies the red bullet is removed indicating the referral has been viewed 
	Then User closes the app and reopen and go back to the "<refCategory>" that contains the referral that was just viewed 
	And User logs out from the Briefcase
	
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
	
	Then User goes back to the "<refCategory>" that contains the referral that was just viewed and verify the bullet does not display 
	
	Examples: 
		|courtId  |refCategory       |
		|CMKA     |Motion            |
		
		
		
		
		
		
		
	@AMB-2354
Scenario Outline: 
	A red bullet icon displays next to referrals which the user has not viewed yet. 
	 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. 
	  This task is to automate the display of the red bullet icon.

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |

	Then I select a user
	|role              |briefcaseUser       |
	|Staff Attorneys   |Brown, Benjamin     |
	When User selects a  "<refCategory>" 
	Then User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page 
	# Verifies the red bullet is removed indicating the referral has been viewed 
	Then User closes the app and reopen and go back to the "<refCategory>" that contains the referral that was just viewed 
	And User logs out from the Briefcase
	
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
		
	Then I select a user
	|role              |briefcaseUser       |
	|Staff Attorneys   |Brown, Benjamin     |
	
	Then User goes back to the "<refCategory>" that contains the referral that was just viewed and verify the bullet does not display 
	
	Examples: 
		|courtId  |refCategory   |
		|CMKA     |Senior        |
		
		
		
		
		
		
		
		
		
		
		

		