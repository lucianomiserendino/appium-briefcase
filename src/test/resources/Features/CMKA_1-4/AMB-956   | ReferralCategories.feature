Feature: Referral Categories display on the dashboard for the judge,Badge numbers in the navigation 


Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "chambers courtney" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	
@Smoke 
@AMB-956 
Scenario Outline: 
	Referral categories display on the dashboard for the judge and SAs
	And User Observes the referral categories that display on "<dbType>" and on the dashboard using  "<judgeName>" and "<PE_RT_CODE>". 
	
	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
	Examples: 
		|judgeName  |dbType       |PE_RT_CODE|category         |case   |
		|Colloton   |CMKA         | jud      |MOTIONS_PETITIONS|15-3314|
		
		
		@Smoke 
		@AMB-1230 
		Scenario: 
			Verify Badge numbers in the navigation
			Given Verify the number of new items that displays in the red badge in the navigation  match the number of new items listed on the Dashboard page. 
			
			
			
			
			
			
			
	