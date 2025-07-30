@Regression
Feature: Ability to enter a document description in docWP DPF, docWPText TPF

@AMB-3648 @AMB-2960 @smoke
Scenario: 
If the user selects a file to upload in the docWP DPF the name of the file is displayed at the left, 
and they are able to add a new document name in the "Enter Description" field.  Verify that user is able to enter a name/description
 and submits the transaction and the name is saved to the DB.
	Verify Briefcase supports the docWPText TPF, which allows the dictionary designer to add information about the documents filed into the docket text.
	
	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
		
        Then user selects a category and case with Proposed Orders
		    
	    When user verifies Proposed Orders are downloaded
	
        Then User selects action that contains docWP
	    |dpf       |courtId    |
	    |docWP     |test       |
	    
	  	When user dockets docWP dpf
	    
	    And user verifies Briefcase supports the docWPText TPF
	    