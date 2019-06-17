Feature: Terminated Referrals removed with Autosync 

@AMB-1187 
Scenario Outline: 
	An auto sync feature is available in Briefcase that automatically displays any new referrals, 
removes any terminated document and retrieves new document information after a specified amount of time that is stored in the site table
 variable briefcaseAutoSyncMinutes.  This task is to automate the autosync feature for new and terminated referrals.


	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User selects "<refCategory>" and "<caseNum>" 
	Then User  sets the chambers_case_to_referral.ccr_date_end date field to today's date  in "<dbType>" , using  "<peID>" and  "<cmr_cyv_code>", indicating the referral "<caseNum>" is terminated. 
	
	#Then User goes to the Dashboard page in Briefcase.User  executes MobileBriefcaseDataUpdater.Waits the number of minutes stored in the briefcaseAutoSyncMinutes site table variable 
	
	
	Examples: 
		|environment   |userName          |password  |server  |refCategory       | caseNum  |dbType|peID|cmr_cyv_code|
		|INTEGRATION   |judge werner      |Test2020! |CMKA    |TEST_AUTOMATION   | 15-2594  |CMKA  |34  |autotst      |
		
		
			
	#Then User navigates to the "<refCategory>" and verify the referral "<caseNum>" does not display anymore in UI because it was terminated and "<dbType>" CCR_DATE_END is not null, use "<peID>" 
	#Given  User Navigates to  "<environment>" environment 
	#When  User enters Credentials to Login "<userName>" and "<password>" 
	#And User clicks on Send Key to Device 
	#Then User navigates to MobileBrifcase App 
	#And  user selects a "<server>" 
	#When User selects Judge,  "<refCategory>" and  "<caseNum>" 
	#3.  Navigate back to the Dashboard.
	#4.  Un-terminate the referral in scenario 1 by removing the date from ccr_date_end field.
	#5.  Execute this URL: https://cms-ecf-cmka.isso.dcn/cmecf/servlet/MobileBriefcaseDataUpdater?debug=y&doScrub=y
	#6.  Wait the number of minutes stored in the briefcaseAutoSyncMinutes site table variable.
	#7.  Navigate to the referral category and verify the referral displays.
	
	