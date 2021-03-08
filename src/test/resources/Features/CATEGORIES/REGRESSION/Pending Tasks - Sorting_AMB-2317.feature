@AMB-2317
Feature: Pending Tasks - Sorting of the categories in My Assignments folder

Scenario: 

The referral categories displayed in the various pending tasks folders should be sorted
 in the same way as they are in the left-hand navigation.
       
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2024!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |
		
	When User selects a  "Pending" 
	