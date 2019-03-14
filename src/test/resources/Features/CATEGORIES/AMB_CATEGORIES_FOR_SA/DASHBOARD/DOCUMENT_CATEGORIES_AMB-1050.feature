Feature: Verify Document Categories display for Staff Attorneys


Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Staff_Attorneys" and  name "Brown, Benjamin" 
	
@AMB-1050 
Scenario: 
	If selecting a referral, the documents display on the referral detail page.  They are grouped by document category.
	   Each document category is a collapsible panel.  This task is to verify the correct document categories and number of docs in each
	    category display for a selected referral. 
	
	Given  User selects assignment type "Senior Staff Attorney" 
	Then User selects category "Anders Cases" and "15-3015" 
	And After selecting "Anders Cases" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB "CMKA" 
	
		