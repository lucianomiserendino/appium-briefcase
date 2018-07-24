Feature: Assignment Categories display on the dashboard for SAs, Referral categories display for SAs,Verify Document Categories display

Background: 

		Given  User Navigates to environment 
	When  User enters Credentials to Login 
        |userName			|password	|
		|SysadminKasabolotova|Asalta6268!z|
	
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	Given  user selects a "<server>" 


@Regression
@AMB_1047
Scenario: 
	Assignment Categories display on the dashboard for SAs
	Given User selects staff attorney Benjamin Brown in the user's list
	Then User observes the assignment categories that display on the dashboard. 
	

@Regression
@AMB_1049
	Scenario: 
	 Once an assignment category is selected, referrals are grouped based on referral categories. 
	 Each referral category is displayed as a collapsible panel. 
	 This task is to verify each referral category is displayed and the number of referrals is correct for each category.

     Given User selects SA Benjamin Brown and assignment type "Senior Staff Attorney"
    When User observes there are six referral categories listed. 

    
    
    