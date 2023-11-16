Feature: docWPText TPF

@AMB-3648
Scenario: 
	Verify Briefcase supports the docWPText TPF, which allows the dictionary designer to add information about the documents filed into the docket text.
	
		#Given I am logged into Briefcase 
	#	|environment|userName| password |courtId|
	#	|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
        Then user selects a category and case with Proposed Orders
	
	   # Then sync all the documents for the selected case
	    
	    When user verifies Proposed Orders are downloaded
	    
		#Then User  selects an action and verifies the name of the action displays in the dark blue banner 
	
		#|courtId|
		#|test   |
		
	
	  	When user dockets docWP dpf

    	Then User navigates to View Case Info, then taps Docket Entries
	    |mbr docWP DMI|
	    
	    And user verifies Briefcase supports the docWPText TPF
	    