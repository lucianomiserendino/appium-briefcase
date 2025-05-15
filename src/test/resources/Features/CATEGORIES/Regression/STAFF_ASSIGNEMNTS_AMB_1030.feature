@AMB-1033 @AMB-1030 @Regression @smoke
Feature: Display of Staff Assignments 




Scenario: 
	If a judge or staff assignment is selected, a new screen displays with the following information:
			1.  Name of the assignee
2.  Type of assignment
3.  Latest assignment date
4.  Referral (relief) to which the assignment is attached
5.  Assignment types and dates
6.  Assignment notes


		
		
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		
   Then User selects a random category
			
   Then User selects a random case
	
	Then User observes a collapsible panel entitled "Assignments" displays 
	
	And User verifies that the Staff Assignments are Displayed correctly and tap on a random assignment
	
	Then verifies the information and notes that display on the page

		
	