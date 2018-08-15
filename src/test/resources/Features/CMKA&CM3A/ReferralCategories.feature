Feature: Referral Categories display on the dashboard for the judge and SAs




@Smoke 
@AMB_956 
@AMB_1120
Scenario Outline: 
	Referral categories display on the dashboard for the judge and SAs
	
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	And User Observes the referral categories that display on "<dbType>" and on the dashboard using  "<judgeName>" . 
	
	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
	Examples: 
		|environment   |userName             |password  | server                                  |judgeName  |dbType       |
		|Integration   |chambers courtney    |Test2020! | Appellate DC Development - CMKA         |Colloton   |CMKA         | 
		|Integration   |KristenStaffAttorney |Test2022! | Appellate DC Development - CMKA         |434        |CMKA-StaffAtt| 
		#|Testing       |Chambers Haenni      |Test2017! |Appellate DC Installation Testing - CM3A |Williams   |CM3A         | 
		
		
		
		
		
		
	