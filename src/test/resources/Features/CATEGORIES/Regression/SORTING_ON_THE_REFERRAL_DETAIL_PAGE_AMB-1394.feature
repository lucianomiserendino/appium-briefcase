@Regression @AMB-1394 @smoke
Feature: Sorting on the Referral Detail Page 

 
Scenario: 
	There is a sort field in the chm_mobile_docs table that the courts can set for sorting Document categories.
	  Briefcase should this field for sorting. Briefcase needs to be updated to sort document categories 
	  based on the chm_mobile_doc.cmd_sort field
	

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
	
   Then User selects a random category
			
   Then User selects a random case
		
	Then User expands/collapse panel
		
	Then User verifies  Document Categories are sorted on the referral detail page and each document for a specific category is listed and ordered by the filed date.
	
	

		
		
		