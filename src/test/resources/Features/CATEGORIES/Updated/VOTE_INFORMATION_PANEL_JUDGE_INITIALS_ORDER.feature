@AMB-2534 @Regression @smoke
Feature:  Judge initials order in Vote Information panel	
Scenario: the judges' initials in the Vote Information panel are supposed to be displayed based on panel_to_judge.pj_judge_order,
 and in the event those values don't exist then the judges' initials are displayed by judge.ju_seniority_sort. 
 
 
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
   Then User selects a random category
			
   Then User selects a random case
		
     Given User observes the Vote Information panel displays.   This should only display if the referral requires voting 
	 Then User verifies that the judges' initials in the Vote Information are sorted based on panel_to_judge.pj_judge_order or by judge.ju_seniority_sort
		|courtId|
		|test   |
						
	