Feature: Saving Document Annotations


Background:

		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		

	#Then I select a user 
	#	|userType   |personrole        |jud     |
	#	|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		

@AMB-2977
Scenario: 
Testing  Document Annotations
		Then I verify that the toggles on the settings page under the PDF Options heading entitled Back up Annotations to CM/ECF, and Allow staff to view annotated documents are turned on by default
	
