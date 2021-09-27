@AMB-2700 
Feature: Sync documents for a specific Referral 


Scenario Outline: 
	Verify the individual case referrals have a Sync link in the Case Information panel that downloads the documents in that particular case. 
	
	
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
	Then User selects "TEST_AUTOMATION" and "21-3877" 
	
		|courtId|
		|test   |
	Examples: 
		|pe_id        |PE_RT_CODE|
		|Colloton     |jud       |
		
		
		
		
		
