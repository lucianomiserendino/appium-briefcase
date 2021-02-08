@Regression @AMB-1047
Feature: Assignment categories display on the dashboard for Staff Attorneys



@Smoke
Scenario: 
	Staff attorney assignments are court definable in the stfaty_assign_val table. 
  Staff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). 
   This is how the assignment category is obtained. 
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
		
	Then I select a user
	|role              |briefcaseUser       |
	|Staff Attorneys   |Brown, Benjamin     |
	Given User verifies Data is displayed on the Dashboard, retrieves categories from db ,'RA_PE_ID' : "434" 
	|courtId|
	|test   |
	

Scenario: 
	Staff attorney assignments are court definable in the stfaty_assign_val table. 
  Staff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). 
   This is how the assignment category is obtained. 
	Given I am logged into Briefcase 
		|environment    |userName        | password |courtId|
		|Integration    |sysadmin  haenni| Test2021!|test   |
		
	Then I select a user
	|role              |briefcaseUser |
	|Staff Attorneys   |Bono, Bonnie  |
	Given User verifies Data is displayed on the Dashboard, retrieves categories from db ,'RA_PE_ID' : "434" 
	|courtId|
	|cmja   |
	

