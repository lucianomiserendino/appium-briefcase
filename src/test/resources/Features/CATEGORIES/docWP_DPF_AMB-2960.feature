Feature: Ability to enter a document description in docWP DPF

@Smoke @AMB-2960
Scenario: If the user selects a file to upload in the docWP DPF the name of the file is displayed at the left, 
and they are able to add a new document name in the "Enter Description" field.  Verify that user is able to enter a name/description
 and submits the transaction and the name is saved to the DB. 
       
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
	
	Then User  selects an action and verifies the name of the action displays in the dark blue banner 
	
		|courtId|
		|test   |
		
	Then User selects one of the proposed orders, enters a description, submits the transaction, verify name is saved to the DB. 