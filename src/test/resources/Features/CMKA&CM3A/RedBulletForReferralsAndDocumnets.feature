Feature: Red bullet displays for new referrals/documnets and does not display for viewed referrals/documnets 

Scenario: 
	A red bullet icon displays next to referrals/documnets which the user has not viewed yet. 
 Once the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed.
   This task is to automate the display of the red bullet icon.

Given  Login as judge werner//Test2018! on cmka
Given    Select a category that has unviewed referrals
Given    Verify that the red bullet icon displays next to any unviewed referrals
Given    Tap on a referral and then got back to the referral list page.  Verify the red bullet is removed indicating the referral has been viewed.
Given    Close the app and reopen and go back to the category that contains the referral that was just viewed.
Given    Verify the red bullet does not display.
Given    Log out of the app and then back in.  Go back to the category that contains the referral that was just viewed and verify the bullet does not display.