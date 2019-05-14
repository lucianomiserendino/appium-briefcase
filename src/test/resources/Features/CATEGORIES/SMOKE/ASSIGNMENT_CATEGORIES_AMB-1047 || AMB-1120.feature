Feature: Assignment categories display on the dashboard for Staff Attorneys 



@Smoke 
@AMB-1047 
Scenario: 
	Staff attorney assignments are court definable in the stfaty_assign_val table. 
  Staff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). 
   This is how the assignment category is obtained. 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Staff_Attorneys" and  name "Brown, Benjamin" 
	Given User verifies Data is displayed on the Dashboard, retrieves categories from "CMKA" ,'RA_PE_ID' : "434" 
	
	
	
@Smoke 
@AMB-1120 
Scenario: 
	Log in as a staff attorney and verify data is displayed.
	Staff attorney assignments are court definable in the stfaty_assign_val table. 
  Staff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). 
   This is how the assignment category is obtained. 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "KristenStaffAttorney" and "Test2023!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Given User verifies Data is displayed on the Dashboard, retrieves categories from "CMKA" ,'RA_PE_ID' : "434" 
	
	
	
