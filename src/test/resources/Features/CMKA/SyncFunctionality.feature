Feature: Referrals Display when user is offline



@AMB_1052
Scenario Outline:
	 Verify that when a user is offline, their pending referrals still display.
      
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User peformes a sync by tapping the "Sync With CM/ECF" button on the Dashboard page
	#Then User closes Briefcase and go offline (turn wifi off)
	#When User opens a Briefcase, verifies the pending referrals still display and that downloaded documnets are accessible
	
	
		Examples: 
		|environment   |userName             |password  |server                          |
		#|Integration   |judge werner         |Test2018! |Appellate DC Development - CMKA |
		|Integration   |KristenStaffAttorney |Test2022! |Appellate DC Development - CMKA |
		#|Integration   |s haenni             |Test2019! |Appellate DC Development - CMKA |
	