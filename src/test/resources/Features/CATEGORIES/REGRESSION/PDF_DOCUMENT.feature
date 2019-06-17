@AMB @Regression @AMB-1006
Feature: Verify a PDF document can be downloaded and opened in Briefcase 


Scenario Outline: 
	Verify that PDF documents can be downloaded from the server to Briefcase and viewed within Briefcase.

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User deletes all docs from the device
	Then User selects "<refCategory>" and "<caseNum>" 
    Then User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase
	
	Examples: 
		|environment   |userName          |password  |server         |refCategory             | caseNum |
		|INTEGRATION   |chambers courtney |Test2023! |CMKA           |MOTIONS_PETITIONS       | 18-12418|
		
