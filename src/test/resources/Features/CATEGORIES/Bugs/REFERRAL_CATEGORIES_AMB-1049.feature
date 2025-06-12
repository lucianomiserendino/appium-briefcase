@AMB-1049 @Regression
Feature: Referral categories display on the dashboard for Staff Attorneys

  Background: 
  
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole       |stf      |user     |
		|stf        |Staff Attorneys  |test     |sysadmin |  
		

  Scenario: 
    Assignment categories are listed on the Dashboard page for staff attorneys.
    Once an assignment category is selected, referrals are grouped based on
    referral categories. Each referral category is displayed as a collapsible
    panel. This task is to verify each referral category is displayed and the
    number of referrals is correct for each category

  
  	Then User selects random stf Aty category
		|courtId|
		|test   |
    When User observes correct referral categories listed for Staff Attorney
 
