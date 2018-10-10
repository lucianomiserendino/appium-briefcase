Feature:  Login as a Staff attorney: Assignment Categories display on the dashboard for SAs, Referral categories display for SAs 


@Smoke 
@AMB-1047 
@AMB-1049 
@AMB-1120 
Scenario Outline: 
	Login as a Staff attorney and verify data is displayed on the Dashboard
 			Staff attorney assignments are court definable in the stfaty_assign_val table.   Staff attorney referrals are stored in the stfaty_mobile_referral table.
 There is a FK to the sftaty_assign_val table (smr_sfa_code).  This is how the assignment category is obtained.
Assignment categories are listed on the Dashboard page for staff attorneys.  Once an assignment category is selected, referrals are 
grouped based on referral categories.  Each referral category is displayed as a collapsible panel.  This task is to verify each referral category is
 displayed and the number of referrals is correct for each category.

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User verifies Data is displayed on the Dashboard, retriev categories from "<dbType>" 
	Given User selects assignment type "Senior Staff Attorney" 
	When User observes there are six referral categories listed. 
	
	Examples: 
		|environment   |userName             |password  |server                          |dbType |
		|Integration   |KristenStaffAttorney |Test2022!|Appellate DC Development - CMKA  |CMKA   |
		
		
		
		
		
		