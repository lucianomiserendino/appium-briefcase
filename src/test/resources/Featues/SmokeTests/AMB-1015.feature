Feature: Bookmark functionality

Background:

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	

	

@AMB_1015	
Scenario:
    Functionality that enables users to bookmark referrals
   Given  User selects a referral category on the dashboard page (i.e., Motions/Petitions, Petitions for Rehearing, etc.)
   When  User selects a case and tap on the bookmark icon in the top red banner
   Then   User Verifies the bookmark icon displays in the navigation and on the dashboard page.
   And  User  Verifies tapping on the bookmark icon in the navigation or on the dashboard page displays the bookmarked referral under the referral category heading.
  Then User  taps on the bookmark icon next to the case you just bookmarked and verify the case is removed from the bookmark category.
	