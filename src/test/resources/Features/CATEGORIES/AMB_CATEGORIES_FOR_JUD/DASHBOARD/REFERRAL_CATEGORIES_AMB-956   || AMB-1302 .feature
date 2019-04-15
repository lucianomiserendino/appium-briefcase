Feature:  Referral Categories display on the dashboard for the judge


@Smoke_ 
@AMB-956 
@AMB-1302 
Scenario Outline: 
	Referral categories display on the dashboard for the judge and SAs
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	And User Observes the referral categories that display on "<dbType>" and on the dashboard using  "<judgeName>" and "<PE_RT_CODE>". 
	
	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
	Examples: 
		|environment |userName          |password  |judgeName  |dbType       |PE_RT_CODE|server|
		|INTEGRATION |chambers courtney |Test2022! |Colloton   |CMKA         | jud      |CMKA  |
		#|INTEGRATION |JAHaenni          |Test2021! |Benton|    |CMKA         | jud      |CMKA  |  
		
		

			
			
			
			
	