Feature: View restricted documents when pdfShowRstrcDocToSysAdmin = N 

@Smoke @AMB-2607 
Scenario Outline: 
	If an admin user attempts to download and view restricted documents while pdfShowRstrcDocToSysAdmin = N, 
the user should be presented with the message: "For administrators, the most likely cause for this error is the judge annotated the original and you do not have access to the annotated version.". 
IF pdfShowRstrcDocToSysAdmin = Y all restricted documents and annotations should be available.


	Given User is logged into Briefcase,   "<environment>",  "<userName>",  "<password>",  "<courtId>" 
	Then User selects "PETITION" and "20-42410" 
	And User observes annotated documents have a green plus next to them and taps on it to expand the original and annotated documents 
	
	
	Examples: 
		|environment    |userName    | password |courtId|
		|Integration    |Judge Werner| Test2026!|CMKA   |
		#|Integration    |JAHaenni    | Test2025!|CMKA   |