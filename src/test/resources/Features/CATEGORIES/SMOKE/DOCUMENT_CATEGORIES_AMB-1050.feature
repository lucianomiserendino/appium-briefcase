Feature: Verify Document Categories display for Staff Attorneys


Background: 

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole       |stf      |user     |
		|stf        |Staff Attorneys  |test     |sysadmin | 
	
@AMB-1050 @Smoke @Regression
Scenario: 
	If selecting a referral, the documents display on the referral detail page.  They are grouped by document category.
	   Each document category is a collapsible panel.  This task is to verify the correct document categories and number of docs in each
	    category display for a selected referral. 
	
		Then User selects random stf Aty category
		|courtId|
		|test   |
		Then User expands/collapse panel
		
	Then User selects a sub category
	And User verifies the document categories and the number of docs displayed for each category matches the number of docs in DB" 
	#Then Verify that swiping over a document in either direction deletes the document
	
		
		
		
@AMB-3498 
Scenario: 
Staff Attorney Test: If the "Replace Author Supplied Hyperlinks" toggle is set to "on/yes" the browser selected by the original author in the PDF should be 
replaced by the browser selected by the Briefcase user as specified in the Citelink Search Engine pane.  
     
     
     
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole       |stf      |user     |
		|stf        |Staff Attorneys  |test     |sysadmin | 
		
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
	
	