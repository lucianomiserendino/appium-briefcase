Feature: Assignment Categories display on the dashboard for SAs, Referral categories display for SAs,Verify Document Categories display 




@AMB_1047 
@AMB_1049 
Scenario Outline: 
	Assignment Categories display on the dashboard for SAs
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Given User selects staff attorney Benjamin Brown in the user's list 
	Then User observes the assignment categories that display on the dashboard. 
	Given User selects SA Benjamin Brown and assignment type "Senior Staff Attorney" 
	When User observes there are six referral categories listed. 
	
	
	Examples: 
		|environment   |userName             |password     |server                            |
		|Integration   |SysadminKasabolotova |Asalta6268!Z |Appellate DC Development - CMKA   |
		
		

			
    