@Smoke @AMB-1050
Feature: Verify Document Categories display for Staff Attorneys


Background: 

	#Given I am logged into Briefcase 
	#	|environment    |userName| password |courtId|
	#	|Integration    |s haenni| Test2025!|test   |
		
		Then I select a user 
		|userType   |personrole        |stf             |
		|stf        |Staff Attorneys   |Brown, Benjamin |
	
 
Scenario: 
	If selecting a referral, the documents display on the referral detail page.  They are grouped by document category.
	   Each document category is a collapsible panel.  This task is to verify the correct document categories and number of docs in each
	    category display for a selected referral. 
	
	Given  User selects assignment type "Senior Staff Attorney" 
	Then User selects category "Anders Cases" and "15-3015" ,SMR_ASSIGN_PE_ID : "434"
	And After selecting "Anders Cases" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB "CMKA". smr_assign_pe_id: "434" 
	Then Verify that swiping over a document in either direction deletes the document
	
		
		
		
@AMB-3498 
Scenario: 
Staff Attorney Test: If the "Replace Author Supplied Hyperlinks" toggle is set to "on/yes" the browser selected by the original author in the PDF should be 
replaced by the browser selected by the Briefcase user as specified in the Citelink Search Engine pane.  
     
     
     
     	#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |stf  |
		|stf        |Staff Attorneys   |test |
		
	Then User selects random stf Aty category
		|courtId|
		|test   |
		
	Then User expands/collapse panel
	
	#Then User selects sub Category
	#|courtId|
	#|test   |
	#Then User selects a random stf Referral
	Then User selects stf sub Category 
	And After selecting "Anders Cases" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB "CMKA". smr_assign_pe_id: "434" 
	
	