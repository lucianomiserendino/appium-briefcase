@AMB @Smoke @AMB-1049 
Feature: Referral categories display on the dashboard for Staff Attorneys 


Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2022!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Staff_Attorneys" and  name "Brown, Benjamin" 
	

Scenario: 
	Assignment categories are listed on the Dashboard page for staff attorneys.
	Once an assignment category is selected, referrals are grouped based on
	referral categories. Each referral category is displayed as a collapsible
	panel. This task is to verify each referral category is displayed and the
	number of referrals is correct for each category 
	
	Given  User selects assignment type "Senior Staff Attorney" 
	When  User observes there are six referral categories listed on UI and DB "CMKA", smr_assign_pe_id: "434"
