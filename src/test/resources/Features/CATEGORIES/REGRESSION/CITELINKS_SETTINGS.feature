Feature: citelink settings

@Smoke @AMB-2761 @k
Scenario: 

Verify the cached documents are not deleted and the user is presented with the message "If you change any Citelink settings, documents cached
 on this device will not reflect the change unless deleted and downloaded again.", "OK"" if the cite link settings change
       
	#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	And Verify the individual case referrals have a Sync link in the Case Information panel that downloads the documents in that particular case.
	
	Then Verify the cached documents are not deleted and the user is presented with the message if the cite link settings change