@AMB @Smoke @AMB-956 @AMB-1302
Feature:  Referral Categories display on the dashboard for the judge

 
Scenario Outline: 
	Referral categories display on the dashboard for the judge 
	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
	And User Observes the referral categories that display on "<dbType>" and on the dashboard using  "<judgeName>" and "<PE_RT_CODE>". 
	
	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
	Examples: 
		 |judgeName  |dbType       |PE_RT_CODE|server|
		 |Colloton   |CMKA         | jud      |CMKA  |
		#|INTEGRATION |JAHaenni          |Test2021! |Benton|    |CMKA         | jud      |CMKA  |  
		
		
	
			
			
			
	