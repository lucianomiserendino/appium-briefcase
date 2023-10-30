Feature: Saving Document Annotations


Background:

		Given I am logged into Briefcase 
		|environment|userName| password |courtId|
	|test       |test    | test     |test   |
		

	#Then I select a user 
	#	|userType   |personrole        |jud     |
	#|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		
	
		

@AMB-2977
Scenario: 
Testing  Document Annotations

        Then User expands/collapse panel
        
	   Then User downloads/opens a random document from Case Detail page  

       And User verifies both the top banner editing icons and the editing tool palette are easily visible in the PSPDFKit.
         
         
	   