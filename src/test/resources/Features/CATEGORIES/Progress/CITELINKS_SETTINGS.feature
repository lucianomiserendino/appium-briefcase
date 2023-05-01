Feature: citelink settings

@Smoke @AMB-2761 @k
Scenario: 

Verify the cached documents are not deleted and the user is presented with the message "If you change any Citelink settings, documents cached
 on this device will not reflect the change unless deleted and downloaded again.", "OK"" if the cite link settings change
       
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	And Verify the individual case referrals have a Sync link in the Case Information panel that downloads the documents in that particular case.
	
	Then Verify the cached documents are not deleted and the user is presented with the message if the cite link settings change
	
	
@AMB-3108 
Scenario: 
Judge Test: If the "Replace Author Supplied Hyperlinks" toggle is set to "on/yes" the browser selected by the original author in the PDF should be 
replaced by the browser selected by the Briefcase user as specified in the Citelink Search Engine pane.  

		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
     When User switches the Use My CM/ECF Settings toggle off, select a random Briefcase citelink preferences 
		
Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		
	
    # Then User selects random document
    
    Then User downloads/opens a random document from Case Detail page  
     
     Then User scrolls through the document until finds a citation link, taps it and verifies The link opens to the correct interface
     
    