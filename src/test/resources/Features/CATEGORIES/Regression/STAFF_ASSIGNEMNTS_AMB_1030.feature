@AMB-1033 @AMB-1030 @Regression
Feature: Display of Staff Assignments 




Scenario: 
	If a judge or staff assignment is selected, a new screen displays with the following information:
			1.  Name of the assignee
2.  Type of assignment
3.  Latest assignment date
4.  Referral (relief) to which the assignment is attached
5.  Assignment types and dates
6.  Assignment notes


		
		
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
	
	Then User observes a collapsible panel entitled "Assignments" displays 
	
	And User verifies that the Staff Assignments are Displayed correctly and tap on a random assignment
	
	Then verifies the information and notes that display on the page

	#Given User gets judge's/staff assignment's info from DataBase  by using  "<caseNum>" , "<cha_ju_pe_id>" , "<cmr_cyv_code>" 
	#	|courtId|
	#	|test   |
	#And User selects a judge or staff assignment  and verifies the information and notes that display on the page 
	#Examples: 
	#	|cha_ju_pe_id|cmr_cs_caseid|cmr_cyv_code|caseNum|refCat |
	#	|32          |82226        |motpet      |15-3314|MOTION |
		
		

		
	