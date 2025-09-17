@smoke @AMB-1047 @AMB-1120 @Regression
Feature: Assignment categories display on the dashboard for Staff Attorneys



Scenario: 
	Staff attorney assignments are court definable in the stfaty_assign_val table. 
  Staff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). 
   This is how the assignment category is obtained. 
	

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
		
			
		Then I select a user 
		|userType   |personrole       |stf      |user     |
		|stf        |Staff Attorneys  |test     |sysadmin | 
		
		
	Given User verifies correct assignment categories  display on the dashboard for Staff Attorneys

