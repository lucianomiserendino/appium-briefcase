@Regression 
Feature: Display additional case information screen when selecting an applied case 

@AMB-2636
Scenario:
when a user selects an applied case in the Applied cases panel, the additional case information 
	screen displays 


	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
		
	Then User selects "PETITION" and "20-42500" 
	When User selects an applied case "20-42501" in the Applied cases panel, the additional case information screen displays 
	