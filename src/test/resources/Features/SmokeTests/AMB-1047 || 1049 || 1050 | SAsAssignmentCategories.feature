Feature: Assignment categories display on the dashboard for staff attorneys. 
	Referral categories display for SAs. Verify Document Categories display 


Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Staff_Attorneys" and  name "Brown, Benjamin" 
@CMKA
@Smoke	
@AMB-1047 
Scenario: 
	Staff attorney assignments are court definable in the stfaty_assign_val table. 
  Staff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). 
   This is how the assignment category is obtained. 
	Given User verifies Data is displayed on the Dashboard, retrieves categories from "CMKA" 
@CMKA
@Smoke	
@AMB-1049 
Scenario: 
	Assignment categories are listed on the Dashboard page for staff attorneys.
	Once an assignment category is selected, referrals are grouped based on
	referral categories. Each referral category is displayed as a collapsible
	panel. This task is to verify each referral category is displayed and the
	number of referrals is correct for each category 
	
	Given  User selects assignment type "Senior Staff Attorney" 
	When  User observes there are six referral categories listed. 
@CMKA
@Smoke	
@AMB-1050 
Scenario: 
	If selecting a referral, the documents display on the referral detail page.  They are grouped by document category.
	   Each document category is a collapsible panel.  This task is to verify the correct document categories and number of docs in each
	    category display for a selected referral. 
	
	Given  User selects assignment type "Senior Staff Attorney" 
	Then User selects category "Anders Cases" and "15-3015" 
	And After selecting "Anders Cases" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB "CMKA" 
	
		