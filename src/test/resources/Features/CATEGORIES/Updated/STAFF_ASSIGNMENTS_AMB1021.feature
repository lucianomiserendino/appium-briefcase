@smoke @AMB-1021 @Regression
Feature: Staff Assignments 


Scenario: 
	Staff members can be assigned to referrals and/or just cases. 
	 This task is to verify that staff assignments are displaying on the referral list page


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
	
	

