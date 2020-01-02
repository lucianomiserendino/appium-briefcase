@AMB @Regression @AMB-1006
Feature: Verify a PDF document can be downloaded and opened in Briefcase 


Scenario Outline: 
	Verify that PDF documents can be downloaded from the server to Briefcase and viewed within Briefcase.

		Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
	Then User deletes all docs from the device
	Then User selects "<refCategory>" and "<caseNum>" 
    Then User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase
	
	Examples: 
		 |server         |refCategory             | caseNum |
		 |CMKA           |MOTIONS_PETITIONS       | 18-12418|
		
