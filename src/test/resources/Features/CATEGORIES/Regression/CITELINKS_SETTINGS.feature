@smoke
Feature: citelink settings

@AMB-2761
Scenario: 

Verify the cached documents are not deleted and the user is presented with the message "If you change any Citelink settings, documents cached
 on this device will not reflect the change unless deleted and downloaded again.", "OK"" if the cite link settings change
       

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
			
   Then User selects a random category
			
   Then User selects a random case
   
   
       Then User expands/collapse panel
        
	   Then User downloads/opens a random document from Case Detail page  
	   
       Then User verifies downloaded ("y") document has a green checkmark next to it
  	
	  Then Verify the cached documents are not deleted and the user is presented with the message if the cite link settings change
	
       Then User verifies downloaded ("n") document has a green checkmark next to it
	
	
	
	
	
	
@AMB-3108 
Scenario: 
Judge Test: If the "Replace Author Supplied Hyperlinks" toggle is set to "on/yes" the browser selected by the original author in the PDF should be 
replaced by the browser selected by the Briefcase user as specified in the Citelink Search Engine pane.  

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
		
     When User switches the Use My CM/ECF Settings toggle off, select a random Briefcase citelink preferences 
		
	    
       Then User downloads/opens a random document with ROA link from Case Detail page  
     
     
    