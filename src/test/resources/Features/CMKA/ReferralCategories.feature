Feature: Referral Categories display on the dashboard for the judge

Background: 

	Given  User Navigates to environment 
	When  User enters Credentials to Login 
        |userName			|password	|
		|chambers courtney  | Test2020! |
	
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 


	
@Smoke
@AMB_956 
Scenario Outline: 
	Referral categories display on the dashboard for the judge
	And After user selects a server "<server>" 
	Given User Observes the referral categories that display on the dashboard and DB 
	
		Examples: 
		|server                        |
		|Appellate DC Development - CM5A|
		#|Appellate DC Development - CMKA|

	#NOTE:  The pending tasks category will not be included in this task.  
	#This category is dynamically created if there are judge assignments. 
	# The bookmarked category is also not included in this task.
	
	