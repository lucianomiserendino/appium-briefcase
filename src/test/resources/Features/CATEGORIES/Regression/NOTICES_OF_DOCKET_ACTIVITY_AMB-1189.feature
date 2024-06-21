@Regression @AMB-1189 
Feature: Links in NDA open in Briefcase 



Background:
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
	
	
 	
Scenario: 

	Users can set preferences for the Notices of Docket Activity (NDA) they receive to include links to open documents, 
    notes and case information in Briefcase.  When these links are clicked, the item will open in Briefcase rather
    than in a browser window.  The URL that is used is created by using the value of the site table variable "briefcaseAppLinkRoot"
    followed by information about the docket entry and/or document. 
    
       Then user verifies that following items open in Briefcase from the NDA link
		|Docket_Entry|
		|Document    |
	  |Note        |

		
		
		
		
		
	