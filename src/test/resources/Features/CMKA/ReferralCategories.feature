Feature: Referral Categories display on the dashboard for the judge 

Background: 

	Given  User Navigates to environment 
	When  User enters Credentials to Login 
		|userName			|password	|
		|chambers courtney  | Test2020! |
		
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	
	
@Smoke	
@Smoke_CMKA
@AMB_956_CMKA
Scenario: 
	Referral categories display on the dashboard for the judge
	Given  user selects a server "Appellate DC Development - CMKA" 
	And User Observes the referral categories that display on the dashboard and DB (CMKA) 
	
	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
		
	
@Smoke_CM5A 
@AMB_956_CM5A 
Scenario: 
	Referral categories display on the dashboard for the judge
	Given  user selects a server "Appellate DC Development - CM5A" 
	And User Observes the referral categories that display on the dashboard and DB (CM5A) 
	
	
	
	