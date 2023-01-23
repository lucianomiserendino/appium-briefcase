@Regression 
Feature: Sorting on the Pending Tasks Page 


@AMB-2380 @n
Scenario Outline: 
	As a judge when I view the Pending Clerk's cases I can sort them by case number or by date in decending order.
	
	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
	
	Then I select a user 
		|userType   |personrole        |jud          |
		|judge      |Appellate Judges  |test         |
		
	When User selects a  "Pending Tasks" 
	And  Verify the "<folder>" cases are sorted by date descending order 
	Examples: 
		|folder             |
		|PendingClerkOffice |
		|MyAssignments      |
		|ReferralsAwaiting  |
	
		
		
		@AMB-2317 @AMB-2318 @AMB-2319 
		Scenario Outline: 
			Pending Tasks - Sorting of the categories in My Assignments, Pending Clerk's Filing and Referrals Awaiting Action folders
	The referral categories displayed in the various pending tasks folders should be sorted in the same way as they are in the left-hand navigation. 
	
	#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
	
	Then I select a user 
		|userType   |personrole        |jud          |
		|judge      |Appellate Judges  |test         |
		
			When User selects a  "Pending Tasks" 
			Then User taps on "<folder>" and verify the referral categories are sorted in the same order as the left-hand navigation 
			Examples: 
				|folder             |
				|MyAssignments      |
			#	|PendingClerkOffice |
			#	|ReferralsAwaiting  |
				
				
				
			