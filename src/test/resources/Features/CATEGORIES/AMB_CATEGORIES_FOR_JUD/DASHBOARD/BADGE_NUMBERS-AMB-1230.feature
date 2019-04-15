Feature: Badge numbers in the navigation 


@Smoke 
@AMB-1230 
Scenario: 
	Verify Badge numbers in the navigation 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "chambers courtney" and "Test2022!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Given  Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page. 
	
	
			