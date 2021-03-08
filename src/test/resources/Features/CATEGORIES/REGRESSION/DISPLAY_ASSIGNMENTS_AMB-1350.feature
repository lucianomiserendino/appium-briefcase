@AMB_1350
Feature: Display assignments when briefcaseTargetOnly = n


Scenario: 

	Given User sets the "briefcaseTargetOnly" site var to "n"
	|courtId|userName       | password |environment|
	|test   |sysadmin haenni| Test2021!|Testing    |
	
	#Given I am logged into Briefcase 
		#|environment    |userName| password |courtId|
		#|Integration    |JAHaenni| Test2024!|test   |
		
	#Then I select a user
	#|role              |briefcaseUser|
	#|Appellate Judges  |Colloton     |
	
	#Then User selects "MOTION" and "15-3314" 
	#Then User observes a collapsible panel entitled "Assignments" displays	