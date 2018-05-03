Feature: Referral Categories display on the dashboard for the judge 

Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	#Login as Chambers Courtney // Test2020!
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	
	
@Smoke 
@AMB_956 
Scenario: 
	Referral categories display on the dashboard for the judge
	Given User Observes the referral categories that display on the dashboard and DB 
	
	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
	