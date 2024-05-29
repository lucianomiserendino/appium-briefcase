Feature: docWPText TPF

@AMB-3648
Scenario: 
	Verify Briefcase supports the docWPText TPF, which allows the dictionary designer to add information about the documents filed into the docket text.
	
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
        Then user selects a category and case with Proposed Orders
		    
	    When user verifies Proposed Orders are downloaded
	
	  	When user dockets docWP dpf

    	Then User navigates to View Case Info, then taps Docket Entries
	    
	    And user verifies Briefcase supports the docWPText TPF
	    