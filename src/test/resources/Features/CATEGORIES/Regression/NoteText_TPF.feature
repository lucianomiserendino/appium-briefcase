Feature: Note TPF

@AMB-3647 @Regression @smoke
Scenario: 
Verify the noteText TPF is supported by Briefcase, where the dictionary designer is able to contribute information
 about the note to docket text that appears in docket entries in Briefcase.
Note:  To view docket entries, go to a case referral, tap View Case Info, then tap Docket Entries

       
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
   Then User selects a random category
			
   Then User selects a random case
				
	Then User selects action
	|dpf       |courtId    |
	|note      |test       |
	
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	
	And user verifies an editable Description , Comment , Submit fields are  displayed.  The default description is defined in the Default description parameter of the note DPF 
		|courtId|
		|test   |
		
	Then User navigates to View Case Info, then taps Docket Entries
	
	 And user verifies Briefcase supports the noteText TPF