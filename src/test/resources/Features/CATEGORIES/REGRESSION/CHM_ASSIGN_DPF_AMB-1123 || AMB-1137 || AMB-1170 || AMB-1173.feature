Feature: chmAssign 

@AMB-1123 @AMB-1137 @AMB-1170 @AMB-1173 @AMB-1466 @S @Smoke 
Scenario: 
	This task is to verify that a chambers user is able to create a new staff assignment,
	to verify back-end updates when a new staff assignment is created,
	edit existing staff assignments and verify Back-end after modifying assignment 
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2025!|test   |
		
	Then I select a user 
		|userType   |personrole        |jud          |
		|judge      |Appellate Judges  |test         |
	
	Then User selects 
		|refCategory|caseNumber|
		|test       |test      |
		
	Then User  selects an action and verifies the name of the action displays in the dark blue banner 
	
		|courtId|
		|test   |
	Then user creates a new assignment, checks the back-end, edits existing assignment and verifies db is updated properly 

	
	#	|dbType |el_id     |cha_ju_pe_id|cmr_cs_caseid|cmr_cyv_code|caseNum |refCat   |dpfName  |
	#|CMKA   |3116      |32          |83393        |prhr        |20-72320|PETITION |chmAssign|
	#	|CMKA   |3116      |32          |82226        |CMKA  |prhr        |15-3314|PETITION |chmAssign|
	
	
