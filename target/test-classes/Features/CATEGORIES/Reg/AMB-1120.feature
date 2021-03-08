@Smoke @AMB-1120
Feature: Log in as a staff attorney and verify data is displayed. 

	

Scenario: Log in as a staff attorney and verify data is displayed.

	Given I am logged into Briefcase 
		|environment    |userName             | password |courtId|
		|Integration    |KristenStaffAttorney | Test2026!|test   |
	Given User verifies Data is displayed on the Dashboard, retrieves categories from db ,'RA_PE_ID' : "434" 
	|courtId|
	|test   |	
	
	