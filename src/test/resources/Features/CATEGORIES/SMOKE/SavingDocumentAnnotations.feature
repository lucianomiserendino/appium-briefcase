@Regression
Feature: Saving Document Annotations


Background:

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		
	
		

@AMB-2977 @AMB-3481
Scenario: 
Testing  Document Annotations

        Then User expands/collapse panel
        
	   Then User downloads/opens a random document from Case Detail page  

       #This step also verifies if the correct pe_id is saved in the mbr_annot_to_doc table after the judge user annotates a doc
       And User verifies both the top banner editing icons and the editing tool palette are easily visible in the PSPDFKit.
         
         
	   