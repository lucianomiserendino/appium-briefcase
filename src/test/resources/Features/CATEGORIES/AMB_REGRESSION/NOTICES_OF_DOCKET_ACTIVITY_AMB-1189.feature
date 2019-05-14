Feature: Links in NDA open in Briefcase 



Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When   User enters Credentials to Login "judge werner" and "Test2020!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	
#@Regression 	
@AMB-1189 
Scenario Outline: 

	Users can set preferences for the Notices of Docket Activity (NDA) they receive to include links to open documents, 
    notes and case information in Briefcase.  When these links are clicked, the item will open in Briefcase rather
    than in a browser window.  The URL that is used is created by using the value of the site table variable "briefcaseAppLinkRoot"
    followed by information about the docket entry and/or document. 
    
	Then user  selects  case "<case>" that is in Briefcase for a judge.( "<dbType>" ) Verifies the docket entry page in Briefcase displays the docket entry( Open a Docket Entry in Briefcase from the NDA link) 
	Then user verifies the document opens in briefcase by using the same "<case>" and "<dbType>" (Open a document in Briefcase from the NDA link). 
	Then  user verifies the note opens in Briefcase by using the same "<case>" and "<dbType>"  (Open a note in Briefcase from the NDA link) 
	Examples: 
	
		|case    | dbType |
		|18-92018| CMKA   |