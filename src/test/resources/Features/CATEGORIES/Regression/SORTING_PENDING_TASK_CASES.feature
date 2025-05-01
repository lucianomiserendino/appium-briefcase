@Regression @pending
Feature: Sorting on the Pending Tasks Page 

Background: 

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
@AMB-2380
Scenario: 
	As a judge when I view the Pending Tasks sub folder cases I can sort them by case number or by date in decending order.
		
	When User selects a  "Pending Tasks" 
	And  Verify the sub-folder cases are sorted by date descending order 

	
		
		
		@AMB-2317 @AMB-2318 @AMB-2319
		Scenario: 
			Pending Tasks - Sorting of the categories in My Assignments, Pending Clerk's Filing and Referrals Awaiting Action folders
	The referral categories displayed in the various pending tasks folders should be sorted in the same way as they are in the left-hand navigation. 
		
			When User selects a  "Pending Tasks" 
			Then User taps on sub - folder and verify the referral categories are sorted in the same order as the left-hand navigation 

				
		
		@AMB-3454
Scenario: 
Pending Tasks displays at the top
		
     Then Verify the Pending Tasks category (if available) will always display at the top of the Dashboard 
     Then user taps on left-hand navigation "Expand" arrows 
     And Verify the Pending Tasks category (if available) will always display at the top of the left navigation 
     
			
			