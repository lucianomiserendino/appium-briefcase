@AMB @Regression @AMB-1006 @a
Feature: Verify a PDF document can be downloaded and opened in Briefcase 


Scenario Outline: 
	Verify that PDF documents can be downloaded from the server to Briefcase and viewed within Briefcase.

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
	Then User deletes all docs from the device
	Then User selects "<refCategory>" and "<caseNum>" 
    Then User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase
	
	Examples: 
		 |server         |refCategory | caseNum |
		 |CMKA           |MOTION      | 18-12418|
		
