Feature: Login as a Staff attorney 


@Smoke 
@AMB-1046 
Scenario Outline: 
	The Sync button is used to download any pending referrals and documents.  
	This task is just to verify that when tapping/clicking the Sync button for a judge or staff attorney, that the sync completes.
 

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User licks/Tap Sync with CM/ECF button on the Dashboard page and verify the Sync completes. 
	
	
	Examples: 
		|environment   |userName             |password  |server                           | 
		|Integration   |chambers courtney    |Test2021!|Appellate DC Development - CMKA   |
		#|Integration   |KristenStaffAttorney |Test2022!|Appellate DC Development - CMKA   |
		