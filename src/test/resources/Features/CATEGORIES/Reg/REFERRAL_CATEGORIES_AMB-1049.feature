@Smoke @AMB-1049
Feature: Referral categories display on the dashboard for Staff Attorneys 


Background: 

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
		
	Then I select a user
	|role              |briefcaseUser       |
	|Staff Attorneys   |Brown, Benjamin     |
Scenario: 
	Assignment categories are listed on the Dashboard page for staff attorneys.
	Once an assignment category is selected, referrals are grouped based on
	referral categories. Each referral category is displayed as a collapsible
	panel. This task is to verify each referral category is displayed and the
	number of referrals is correct for each category 
	
	Given  User selects assignment type "Senior Staff Attorney" 
	When  User observes there are six referral categories listed on UI and DB, use smr_assign_pe_id: "434"
    |courtId|
	|test   |